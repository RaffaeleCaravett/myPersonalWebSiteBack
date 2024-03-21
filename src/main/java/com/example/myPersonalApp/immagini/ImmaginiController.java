package com.example.myPersonalApp.immagini;

import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.ImmaginiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        @PreAuthorize("hasAuthority('Admin')")
    public Immagini save(@RequestBody @Validated ImmaginiDTO immaginiDTO, BindingResult validation, @RequestParam("immagine") MultipartFile body) throws IOException {
       if(validation.hasErrors()){
           throw new BadRequestException(validation.getAllErrors());
       }
        return immaginiService.save(immaginiDTO,body);
    }
    @PutMapping("/{id}/{talk_id}")
        @PreAuthorize("hasAuthority('Admin')")
    public Immagini putById(@RequestBody ImmaginiDTO immaginiDTO,BindingResult validation,@PathVariable long id,@PathVariable long talk_id,@RequestParam("immagine") MultipartFile file){
     if(validation.hasErrors()){
         throw new BadRequestException(validation.getAllErrors());
     }
     return immaginiService.putById(id,talk_id,immaginiDTO,file);
    }
@DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public boolean deleteById(@PathVariable long id){
        return immaginiService.deleteById(id);
}

}
