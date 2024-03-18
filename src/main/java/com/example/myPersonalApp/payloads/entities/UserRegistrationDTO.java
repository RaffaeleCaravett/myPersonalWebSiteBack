package com.example.myPersonalApp.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDTO(
        @NotEmpty(message = "Nome obbligatorio")
        String nome,
        @NotEmpty(message = "Cognome obbligatorio")
        String cognome,
        @NotEmpty(message = "Email obbligatorio")
        String email,
        @NotNull(message = "Età necessaria")
        int eta,
        @NotEmpty(message = "Password necessaria")
        String password
) {
}
