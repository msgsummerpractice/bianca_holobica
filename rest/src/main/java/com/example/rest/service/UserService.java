package com.example.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.model.User;
import com.example.rest.repository.IUserRepository;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setFirstname(userDetails.getFirstname());
            existingUser.setLastname(userDetails.getLastname());
            return userRepository.save(existingUser);
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
    public Optional<User> patchUser(Long id, Map<String, Object> updates) {
        return userRepository.findById(id).map(existingUser -> {
            if (updates.containsKey("username")) {
                existingUser.setUsername((String) updates.get("username"));
            }
            if (updates.containsKey("email")) {
                existingUser.setEmail((String) updates.get("email"));
            }
            if (updates.containsKey("password")) {
                existingUser.setPassword((String) updates.get("password"));
            }
            if (updates.containsKey("firstname")) {
                existingUser.setFirstname((String) updates.get("firstname"));
            }
            if (updates.containsKey("lastname")) {
                existingUser.setLastname((String) updates.get("lastname"));
            }
            return userRepository.save(existingUser);
        });
    }
}
