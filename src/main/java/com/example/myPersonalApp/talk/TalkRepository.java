package com.example.myPersonalApp.talk;

import com.example.myPersonalApp.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRepository extends JpaRepository<Talk,Long> {


    Page<Talk> findByCategoria(Pageable pageable,Categoria categoria);
    Page<Talk> findByTitoloContainingIgnoringCase(Pageable pageable,String titolo);
    Page<Talk> findByTitoloContainingIgnoringCaseAndCategoria(Pageable pageable,String titolo,Categoria categoria);

}
