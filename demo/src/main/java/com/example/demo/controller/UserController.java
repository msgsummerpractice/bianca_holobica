package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        logger.info("HTTP GET request received for /users");
        List<User> users = userService.getAllUsers();
        logger.info("The users were returned successfully to the client");
        return users;
    }
}