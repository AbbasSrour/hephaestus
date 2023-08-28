package com.abbassrour.backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {
    @Pattern(regexp = "^[a-zA-Z]*$")
    @Size(min = 3, max = 50)
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    @Size(min = 8, max = 32)
    @NotBlank
    private String password;
}
