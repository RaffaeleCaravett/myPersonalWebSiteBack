package com.example.myPersonalApp.immagini;

import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.ImmaginiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/immagini")
public class ImmaginiController {
    @Autowired
    ImmaginiService immaginiService;


    @PostMapping("")
    public Immagini uploadAvatar(@RequestBody @Validated ImmaginiDTO immaginiDTO, BindingResult validation, @RequestParam("immagine") MultipartFile body) throws IOException {
       if(validation.hasErrors()){
           throw new BadRequestException(validation.getAllErrors());
       }
        return immaginiService.save(immaginiDTO,body);
    }
}
