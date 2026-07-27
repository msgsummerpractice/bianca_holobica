package com.example.rest.service;
import com.example.rest.model.User;
import java.util.List;
import java.util.Optional;
import java.util.Map;


public interface IUserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    Optional<User> updateUser(Long id, User userDetails);
    boolean deleteUser(Long id);
    Optional<User> patchUser(Long id, Map<String, Object> updates);
}
