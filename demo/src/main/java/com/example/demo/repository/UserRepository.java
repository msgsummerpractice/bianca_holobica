package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final List<User> users = new ArrayList<>();
    UserRepository() {
        users.add(new User(1, "John Doe"));
        users.add(new User(2, "Jane Smith"));
        logger.info("UserRepositoryImplementation initialized with sample users");
    }

    public List<User> findAll() {
        logger.debug("Finding all users in the repository");
        return users;
    }

}
