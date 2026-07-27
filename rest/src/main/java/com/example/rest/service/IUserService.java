package com.example.rest.service;
import com.example.rest.UserRequest;
import com.example.rest.UserResponse;
import com.example.rest.UpdateUserRequest;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserResponse> getAllUsers();
    Optional<UserResponse> getUserById(Long id);
    UserResponse createUser(UserRequest request);
    Optional<UserResponse> updateUser(Long id, UpdateUserRequest request);
    boolean deleteUser(Long id);
    Optional<UserResponse> patchUser(Long id, UpdateUserRequest request);
}
