package com.example.myPersonalApp.immagini;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.scheduling.config.Task;

@Entity
@Table(name="immagini")
@Data
public class Immagini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String link;
    @ManyToOne
    @JsonIgnore
    private Task task;
}
