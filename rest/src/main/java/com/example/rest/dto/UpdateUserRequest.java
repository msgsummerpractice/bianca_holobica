package com.example.rest.dto;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    private String username;
    
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;

    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters.")    
    private String firstname;

    @Size(min = 1, max = 50, message = "Lastname must be between 1 and 50 characters.")
    private String lastname;
}
