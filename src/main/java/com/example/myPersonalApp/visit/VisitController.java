package com.example.myPersonalApp.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {


    @Autowired
    VisitService visitService;


    @PostMapping("")
    public Visit save(){
        return visitService.save();
    }

    @GetMapping("")
    public List<Visit> getAll(){
        return visitService.getAll();
    }
}
