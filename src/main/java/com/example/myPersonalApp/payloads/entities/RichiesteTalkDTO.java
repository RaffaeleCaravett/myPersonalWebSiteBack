package com.example.myPersonalApp.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RichiesteTalkDTO(
        @NotEmpty(message = "Nome necessario")
        String nome,
        @NotEmpty(message = "Cognome necessario")
        String cognome,
        @NotEmpty(message = "Email necessaria")
        String email,
        @NotEmpty(message = "Testo necessario")
        String testo,
        @NotEmpty(message = "Link profilo necessario")
        String linkProfilo,
        @NotEmpty(message = "Professione necessario")
        String professione,
        @NotNull(message = "Eta necessaria")
        int eta,
        @NotEmpty(message = "Categoria necessaria")
        String categoria
) {
}
