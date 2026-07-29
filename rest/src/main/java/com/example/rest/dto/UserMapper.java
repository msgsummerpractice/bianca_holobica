package com.example.rest.dto;

import org.springframework.stereotype.Component;

import com.example.rest.model.User;

@Component
public class UserMapper {
    public User toEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        return user;
    }
    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname()
        );
    }

    public void updateEntityFromDto(UpdateUserRequest dto, User entry, boolean isPatch) {
        if (isPatch) {
            if (dto.getUsername() != null) {
                entry.setUsername(dto.getUsername());
            }
            if (dto.getEmail() != null) {
                entry.setEmail(dto.getEmail());
            }
            if (dto.getPassword() != null) {
                entry.setPassword(dto.getPassword());
            }
            if (dto.getFirstname() != null) {
                entry.setFirstname(dto.getFirstname());
            }
            if (dto.getLastname() != null) {
                entry.setLastname(dto.getLastname());
            }
        } else {
            entry.setUsername(dto.getUsername());
            entry.setEmail(dto.getEmail());
            entry.setPassword(dto.getPassword());
            entry.setFirstname(dto.getFirstname());
            entry.setLastname(dto.getLastname());
        }
    }
}
