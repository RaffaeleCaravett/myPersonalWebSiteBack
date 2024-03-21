package com.example.myPersonalApp.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;



    public Visit save(){
        Visit visit = new Visit();
        visit.setData(LocalDate.now());
        return visitRepository.save(visit);
    }

    public List<Visit> getAll(){
        return visitRepository.findAll();
    }
}
