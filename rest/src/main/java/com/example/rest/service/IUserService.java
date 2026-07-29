package com.example.rest.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.rest.dto.UpdateUserRequest;
import com.example.rest.dto.UserRequest;
import com.example.rest.dto.UserResponse;

public interface IUserService {
    List<UserResponse> getAllUsers();
    Optional<UserResponse> getUserById(Long id);
    UserResponse createUser(UserRequest request);
    Optional<UserResponse> updateUser(Long id, UpdateUserRequest request);
    boolean deleteUser(Long id);
    Optional<UserResponse> patchUser(Long id, UpdateUserRequest request);
    Page<UserResponse> getUsers(int page, int size, String sortBy);
}
