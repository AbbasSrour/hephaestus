package com.abbassrour.backend.user.model;


import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 480)
    private String email;

    @Column(nullable = false)
    private String password;
}