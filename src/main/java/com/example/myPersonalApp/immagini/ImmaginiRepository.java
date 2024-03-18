package com.example.myPersonalApp.immagini;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmaginiRepository extends JpaRepository<Immagini,Long> {
}
