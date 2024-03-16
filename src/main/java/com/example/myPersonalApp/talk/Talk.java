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
private String testo;
private Categoria categoria;
@OneToMany(mappedBy = "talk")
private List<Immagini> immagini;
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
}
