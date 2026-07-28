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
    private String type = "Bearer";
    private String username;
    private List<String> roles;
    private boolean mfaRequired;

    public SignInResponse(String token, String username, List<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.mfaRequired = false;
    }
}