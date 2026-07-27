package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    List<User> getTop10UsersByUsername(String username);
    long countUsers();

}
