package com.example.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MfaVerifyRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String code;
}