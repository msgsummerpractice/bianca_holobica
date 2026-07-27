package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @InjectMocks
    private UserRepository userRepository;

    @Test
    @DisplayName("findAll should return a list of users from initial state")
    void findAll_returnsListOfUsers_whenCalled() {
        List<User> result = userRepository.findAll();

        assertThat(result)
                .isNotNull()
                .hasSize(2);

        assertThat(result.get(0).getName()).isEqualTo("John Doe");
    }
}