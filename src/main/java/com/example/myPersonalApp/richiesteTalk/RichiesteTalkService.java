package com.example.myPersonalApp.richiesteTalk;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.enums.Stato;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.RichiesteTalkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiesteTalkService {
    @Autowired
    RichiesteTalkRepository richiesteTalkRepository;

    public RichiesteTalk save(RichiesteTalkDTO richiesteTalkDTO){
        RichiesteTalk richiesteTalk= new RichiesteTalk();

        richiesteTalk.setNome(richiesteTalkDTO.nome());
        richiesteTalk.setCognome(richiesteTalkDTO.cognome());
        richiesteTalk.setEta(richiesteTalkDTO.eta());
        richiesteTalk.setEmail(richiesteTalkDTO.email());
        richiesteTalk.setTesto(richiesteTalkDTO.testo());
        richiesteTalk.setLinkProfilo(richiesteTalkDTO.linkProfilo());
        richiesteTalk.setProfessione(richiesteTalkDTO.professione());
        richiesteTalk.setCategoria(Categoria.valueOf(richiesteTalkDTO.categoria()));
        richiesteTalk.setStato(Stato.Non_letto);
        return richiesteTalkRepository.save(richiesteTalk);
    }

    public Page<RichiesteTalk> getAllPaginated(int page,int size,String orderBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return richiesteTalkRepository.findAll(pageable);
    }

    public Page<RichiesteTalk> getAllPaginatedByCategory(int page,int size,String orderBy,Categoria categoria){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return richiesteTalkRepository.findByCategoria(pageable,categoria);
    }

    public RichiesteTalk leggi(long id){
        RichiesteTalk  richiesteTalk = richiesteTalkRepository.findById(id).orElseThrow(()-> new BadRequestException("Richiesta con id " + id + " non trovata"));
        richiesteTalk.setStato(Stato.Letto);

        return richiesteTalkRepository.save(richiesteTalk);
    }
    
}
