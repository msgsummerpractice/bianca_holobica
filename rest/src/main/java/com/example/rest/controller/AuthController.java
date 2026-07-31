package com.example.rest.controller;

import com.example.rest.dto.*;
import com.example.rest.service.IAuthService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInRequest signInRequest) {
        String mfaCode = authService.authenticateUser(signInRequest);
        return ResponseEntity.ok(Map.of(
                "message", "First step successful. MFA code has been generated.",
                "mfaCode", mfaCode,
                "email", signInRequest.getEmail()
        ));
    }

    @PostMapping("/verify-mfa")
    public ResponseEntity<SignInResponse> verifyMfa(@Valid @RequestBody MfaVerifyRequest verifyRequest) {
        SignInResponse response = authService.verifyMfaAndGenerateToken(verifyRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        String result = authService.registerUser(registerRequest);
        return ResponseEntity.ok(result);
    }
}