package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplementation implements UserRepository {
    private final List<User> users = new ArrayList<>();
    UserRepositoryImplementation() {
        users.add(new User(1, "John Doe"));
        users.add(new User(2, "Jane Smith"));
    }

    public List<User> findAll() {
        return users;
    }
}
