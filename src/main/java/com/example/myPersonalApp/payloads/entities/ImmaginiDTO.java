package com.example.myPersonalApp.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ImmaginiDTO(
        @NotNull(message = "Talk id necessario")
        long talk_id,
        @NotEmpty(message = "Posizione necessaria")
        String posizione
) {
}
