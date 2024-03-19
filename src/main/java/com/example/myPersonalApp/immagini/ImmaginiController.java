package com.example.myPersonalApp.immagini;

import com.example.myPersonalApp.payloads.entities.ImmaginiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/immagini")
public class ImmaginiController {
    @Autowired
    ImmaginiService immaginiService;


    @PostMapping("")
    public String uploadAvatar(@RequestBody ImmaginiDTO immaginiDTO, @RequestParam("immagine_profilo") MultipartFile body) throws IOException {
        System.out.println("Received request - ID: " + id);

        if (body != null) {
            System.out.println("Received file - Name: " + body.getOriginalFilename());
        } else {
            System.out.println("No file received");
        }
        return utenteService.uploadAvatar(id,body);
    }
}
