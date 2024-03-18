package com.example.myPersonalApp.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO(
        @NotEmpty(message = "Email obbligatoria.")
        String email,
        @NotEmpty(message = "Password obbligatoria.")
        String password
) {
}
