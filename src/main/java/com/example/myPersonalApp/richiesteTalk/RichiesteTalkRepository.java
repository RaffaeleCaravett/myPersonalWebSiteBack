package com.example.myPersonalApp.richiesteTalk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RichiesteTalkRepository extends JpaRepository<RichiesteTalk,Long> {
}
