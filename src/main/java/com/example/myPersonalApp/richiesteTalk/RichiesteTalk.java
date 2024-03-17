package com.example.myPersonalApp.richiesteTalk;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="richiesteTalk")
@Data
public class RichiesteTalk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String linkProfilo;
    private String professione;
    private int eta;
    private String testo;
}
