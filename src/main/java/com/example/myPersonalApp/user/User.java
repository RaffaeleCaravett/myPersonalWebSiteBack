package com.example.myPersonalApp.user;

import com.example.myPersonalApp.enums.Role;
import com.example.myPersonalApp.talk.Talk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Talk> talks;
}
