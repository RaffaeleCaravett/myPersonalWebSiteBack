package com.example.myPersonalApp.talk;

import com.example.myPersonalApp.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRepository extends JpaRepository<Talk,Long> {


    Page<Talk> findByCategoria(Categoria categoria);

}
