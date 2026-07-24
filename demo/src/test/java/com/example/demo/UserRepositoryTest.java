package com.example.demo;

import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;
    
    @Test
    @DisplayName("findAll should return a list of users")
    void findAll_ShouldReturnListOfUsers() {
        List<User> mockUsers = List.of(
                new User(1, "John Doe"),
                new User(2, "Jane Smith")
        );
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userRepository.findAll();

        assertThat(result)
                .isNotNull()
                .hasSize(2);

        assertThat(result.get(0).getName()).isEqualTo("John Doe");
        verify(userRepository, times(1)).findAll();
    }
}
