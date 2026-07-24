package com.example.demo.controller;

import com.example.demo.AppSettings;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import org.slf4j.Logger;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Value("${app.welcome.message: Hello Default}")
    private String welcomeMessage;

    private final AppSettings appSettings;

    public UserController(UserService userService, AppSettings appSettings) {
        this.userService = userService;
        this.appSettings = appSettings;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam @Min(value = 1, message = "minId must be >= 1") int minId) {
        logger.info("HTTP GET request received for /users");

        logger.info ("Welcome message from application.properties: {}", welcomeMessage);
        List<User> users = userService.getAllUsers();
        logger.info("The users were returned successfully to the client");

        logger.info("Max Page Size from AppSettings: {}", appSettings.getMaxPageSize());
        logger.info("Feature Enabled from AppSettings: {}", appSettings.isFeatureEnabled());

        return users;
    }
}