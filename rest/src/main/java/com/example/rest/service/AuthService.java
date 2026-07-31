package com.example.rest.service;

import com.example.rest.dto.MfaVerifyRequest;
import com.example.rest.dto.RegisterRequest;
import com.example.rest.dto.SignInRequest;
import com.example.rest.dto.SignInResponse;
import com.example.rest.model.Role;
import com.example.rest.model.User;
import com.example.rest.repository.IRoleRepository;
import com.example.rest.repository.IUserRepository;
import com.example.rest.security.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final Map<String, String> mfaCodeStore = new ConcurrentHashMap<>();

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                           IUserRepository userRepository,
                           IRoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String authenticateUser(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        String otpCode = String.format("%06d", new Random().nextInt(900000) + 100000);
        mfaCodeStore.put(signInRequest.getEmail(), otpCode);

        System.out.println(" MFA CODE " + signInRequest.getEmail() + ": " + otpCode);
        return otpCode;
    }

    @Override
    public SignInResponse verifyMfaAndGenerateToken(MfaVerifyRequest verifyRequest) {
        String storedCode = mfaCodeStore.get(verifyRequest.getEmail());

        if (storedCode == null || !storedCode.equals(verifyRequest.getCode())) {
            throw new RuntimeException("MFA code is invalid or has expired!");
        }

        mfaCodeStore.remove(verifyRequest.getEmail());

        User user = userRepository.findByEmail(verifyRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        var authorities = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles()
                .build();

        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        return new SignInResponse(jwtToken, user.getEmail(), authorities);
    }

    @Override
    public String registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstname(registerRequest.getFirstName());
        user.setLastname(registerRequest.getLastName());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
        user.setRoles(Set.of(userRole));

        userRepository.save(user);
        return "User registered successfully!";
    }
}