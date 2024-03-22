package com.example.myPersonalApp.talk;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.immagini.Immagini;
import com.example.myPersonalApp.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "talks")
@Data
public class Talk {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
private String testo;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
private String titolo;
@OneToMany(mappedBy = "talk")
private List<Immagini> immagini;
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
}
