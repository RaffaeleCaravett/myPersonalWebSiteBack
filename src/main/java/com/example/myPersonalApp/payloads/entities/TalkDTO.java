package com.example.myPersonalApp.payloads.entities;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TalkDTO(
        @NotEmpty(message = "Categoria vuota")
        String categoria,
        @NotNull(message = "User id vuoto")
        long user_id,
        @NotEmpty(message = "Testo vuoto")
        String testo1,
        @NotEmpty(message = "Testo vuoto")
        String testo2,
        @NotEmpty(message = "Testo vuoto")
        String testo3,
        @NotEmpty(message = "Titolo necessario")
        String titolo
        ) {
}
