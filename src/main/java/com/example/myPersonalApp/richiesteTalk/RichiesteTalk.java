package com.example.myPersonalApp.richiesteTalk;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.enums.Stato;
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
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private String testo;
    private Stato stato;
}
