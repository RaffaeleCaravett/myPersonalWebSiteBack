package com.example.myPersonalApp.immagini;

import com.example.myPersonalApp.enums.Position;
import com.example.myPersonalApp.talk.Talk;
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
    private Position position;
    @ManyToOne
    @JoinColumn(name = "talk_id")
    @JsonIgnore
    private Talk talk;
}
