package com.example.myPersonalApp.richiesteTalk;

import com.example.myPersonalApp.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RichiesteTalkRepository extends JpaRepository<RichiesteTalk,Long> {
    Page<RichiesteTalk> findAll(Pageable pageable);

    Page<RichiesteTalk> findByCategoria(Pageable pageable, Categoria categoria);
}
