package com.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SignInResponse {
    private String token;
    private String email;
    private List<String> roles;
    private boolean mfaRequired;

    public SignInResponse(String token, String email, List<String> roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
        this.mfaRequired = false;
    }
}