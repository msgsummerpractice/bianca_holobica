package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("createUser should save and return the user")
    void createUser_returnsUser_whenSaved() {
        User user = createSampleUser(null, "johndoe", "john@example.com");
        User savedUser = createSampleUser(1L, "johndoe", "john@example.com");

        when(userRepository.save(user)).thenReturn(savedUser);

        User result = userService.createUser(user);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("getAllUsers should return list of users")
    void getAllUsers_returnsListOfUsers_whenCalled() {
        List<User> mockUsers = createSampleUserList();

        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userService.getAllUsers();

        assertThat(result).hasSize(2);
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("getUserByUsername should return user when exists")
    void getUserByUsername_returnsUser_whenUserExists() {
        User user = createSampleUser(1L, "johndoe", "john@example.com");
        when(userRepository.findByUsername("johndoe")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByUsername("johndoe");

        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("johndoe");
        verify(userRepository).findByUsername("johndoe");
    }

    @Test
    @DisplayName("getUserByEmail should return user when exists")
    void getUserByEmail_returnsUser_whenEmailExists() {
        User user = createSampleUser(1L, "johndoe", "john@example.com");
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByEmail("john@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("john@example.com");
        verify(userRepository).findByEmail("john@example.com");
    }

    @Test
    @DisplayName("updateUser should update existing user details")
    void updateUser_updatesAndReturnsUser_whenUserExists() {
        User existingUser = createSampleUser(1L, "oldName", "old@example.com");
        User updateDetails = createSampleUser(null, "newName", "new@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updated = userService.updateUser(1L, updateDetails);

        assertThat(updated.getUsername()).isEqualTo("newName");
        assertThat(updated.getEmail()).isEqualTo("new@example.com");
        verify(userRepository).findById(1L);
        verify(userRepository).save(existingUser);
    }

    @Test
    @DisplayName("deleteUser should call repository deleteById")
    void deleteUser_callsRepository_whenCalled() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    @DisplayName("getTop10UsersByUsername should return top 10 users matching pattern")
    void getTop10UsersByUsername_returnsMatchingUsers() {
        List<User> mockUsers = createSampleUserList();
        when(userRepository.findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc("john"))
                .thenReturn(mockUsers);

        List<User> result = userService.getTop10UsersByUsername("john");

        assertThat(result).hasSize(2);
        verify(userRepository).findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc("john");
    }

    @Test
    @DisplayName("countUsers should return total count from repository")
    void countUsers_returnsTotalCount() {
        when(userRepository.countUsers()).thenReturn(10L);

        long count = userService.countUsers();

        assertThat(count).isEqualTo(10L);
        verify(userRepository).countUsers();
    }


    private User createSampleUser(Long id, String username, String email) {
        return new User(id, username, email, "password123", "John", "Doe");
    }

    private List<User> createSampleUserList() {
        return List.of(
            createSampleUser(1L, "johndoe", "john@example.com"),
            createSampleUser(2L, "janesmith", "jane@example.com")
        );
    }
}