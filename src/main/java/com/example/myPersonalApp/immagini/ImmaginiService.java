package com.example.myPersonalApp.immagini;

import com.cloudinary.utils.ObjectUtils;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.ImmaginiDTO;
import com.example.myPersonalApp.talk.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImmaginiService {

    @Autowired
    ImmaginiRepository immaginiRepository;
@Autowired
    TalkRepository talkRepository;

    public Immagini save(ImmaginiDTO immaginiDTO, MultipartFile file) throws IOException {
        try {
                Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("url");

            Immagini immagini = new Immagini();

            immagini.setTalk(talkRepository.findById(immaginiDTO.talk_id()).orElseThrow(()->new BadRequestException("Talk non presente. Impossibile salvare l'immagine")));
            immagini.setLink(imageUrl);

                return immaginiRepository.save(immagini);
            } catch (IOException e) {
                throw new RuntimeException("Impossibile caricare l'immagine", e);
            }
        }
    }
}
