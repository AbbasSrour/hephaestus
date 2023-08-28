package com.abbassrour.backend.user.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
public class UserDTO {
    @UUID
    private String id;

    @Size(min = 3, max = 50)
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String username;

    @Email
    private String email;

    @Size(min = 8, max = 32)
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;
}
