package com.example.demo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import com.example.demo.model.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.repository.IUserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test getAllUsers returns a list of users")
    void getAllUsers_returnsListOfUsers_whenRepositoryReturnsUsers() {
        List<User> mockUsers = createMockUsers();
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userService.getAllUsers();

        assertThat(result)
                .isNotNull()
                .hasSize(2)
                .isEqualTo(mockUsers);

        verify(userRepository, times(1)).findAll();
    }

    private List<User> createMockUsers() {
        return List.of(
            new User(1, "John Doe"),
            new User(2, "Jane Smith")
        );
    }
}