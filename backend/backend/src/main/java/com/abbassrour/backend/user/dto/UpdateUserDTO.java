package com.abbassrour.backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUserDTO {
    @Email
    private String email;

    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String username;

    @Size(min = 8, max = 32)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;
}
