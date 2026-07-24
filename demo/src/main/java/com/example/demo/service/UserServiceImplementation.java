package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
    private final UserRepository userRepository;
    UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.info("Retrieving all users from the service");
        return userRepository.findAll();
    }
}
