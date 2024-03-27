package com.example.myPersonalApp.talk;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.TalkDTO;
import com.example.myPersonalApp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TalkService {
    @Autowired
    TalkRepository talkRepository;
    @Autowired
    UserRepository userRepository;

    public Talk save(TalkDTO talkDTO){
        Talk talk = new Talk();
        talk.setCategoria(Categoria.valueOf(talkDTO.categoria()));
        talk.setUser(userRepository.findById(talkDTO.user_id()).orElseThrow(()->new BadRequestException("User id non presente in database.")));
        talk.setTesto1(talkDTO.testo1());
        talk.setTesto2(talkDTO.testo2());
        talk.setTesto3(talkDTO.testo3());
        talk.setTitolo(talkDTO.titolo());

        return talkRepository.save(talk);
    }

    public Talk putById(long id, TalkDTO talkDTO){
        Talk talk = talkRepository.findById(id).orElseThrow(()->new BadRequestException("User id non presente in database"));
        talk.setTesto1(talkDTO.testo1());
        talk.setTesto2(talkDTO.testo2());
        talk.setTesto3(talkDTO.testo3());
        talk.setTitolo(talkDTO.titolo());
        talk.setCategoria(Categoria.valueOf(talkDTO.categoria()));
        talk.setUser(userRepository.findById(talkDTO.user_id()).orElseThrow(()->new BadRequestException("User id non presente in database.")));
return talkRepository.save(talk);
    }

    public boolean deleteById(long id){
        try {
            talkRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

public Talk getById(long id){
        return talkRepository.findById(id).orElseThrow(()->new BadRequestException("Id non presente in db"));
}

public Page<Talk> getAllPaginated(int page,int size,String orderBy){
    Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
    return talkRepository.findAll(pageable);
}

public Page<Talk> findByCategoria(Categoria categoria,int page,int size,String orderBy){
        Pageable pageable = PageRequest.of(page,size,Sort.by(orderBy));
        return talkRepository.findByCategoria(pageable,categoria);
}

public Page<Talk> findByTitolo(String titolo,int page,int size,String orderBy){
        Pageable pageable = PageRequest.of(page,size,Sort.by(orderBy));
        return talkRepository.findByTitoloContainingIgnoringCase(pageable,titolo);
}
    public Page<Talk> findByTitoloAndCategoria(String titolo,Categoria categoria,int page,int size,String orderBy){
        Pageable pageable = PageRequest.of(page,size,Sort.by(orderBy));
        return talkRepository.findByTitoloContainingIgnoringCaseAndCategoria(pageable,titolo,categoria);
    }
}
