package com.abbassrour.backend.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserFilters {
    private String email;

    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String username;
}
