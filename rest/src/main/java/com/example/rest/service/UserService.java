package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.rest.dto.UpdateUserRequest;
import com.example.rest.dto.UserMapper;
import com.example.rest.dto.UserRequest;
import com.example.rest.dto.UserResponse;
import com.example.rest.model.User;
import com.example.rest.repository.IUserRepository;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    
    @Autowired
    public UserService(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse);
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public Optional<UserResponse> updateUser(Long id, UpdateUserRequest request) {
        return userRepository.findById(id).map(existingUser -> {
            userMapper.updateEntityFromDto(request, existingUser, false);
            User updatedUser = userRepository.save(existingUser);
            return userMapper.toResponse(updatedUser);
        });
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

   @Override
    public Optional<UserResponse> patchUser(Long id, UpdateUserRequest request) {
        return userRepository.findById(id).map(existingUser -> {
            userMapper.updateEntityFromDto(request, existingUser, true); // true -> actualizare parțială
            User updatedUser = userRepository.save(existingUser);
            return userMapper.toResponse(updatedUser);
        });
    }

    @Override
    public Page<UserResponse> getUsers(int page, int size, String sortBy) {
        return userRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)))
                .map(userMapper::toResponse);
    }
}
