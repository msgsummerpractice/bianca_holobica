package com.example.rest.service;
import com.example.rest.dto.SignInRequest;
import com.example.rest.dto.SignInResponse;
import com.example.rest.dto.MfaVerifyRequest;
import com.example.rest.dto.RegisterRequest;


public interface IAuthService {
    String authenticateUser(SignInRequest signInRequest);
    SignInResponse verifyMfaAndGenerateToken(MfaVerifyRequest mfaVerifyRequest);
    String registerUser(RegisterRequest registerRequest);
}
