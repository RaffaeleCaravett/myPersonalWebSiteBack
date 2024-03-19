package com.example.myPersonalApp.user;

import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

@Autowired
UserRepository userRepository;


public User findById(long id){
return userRepository.findById(id).orElseThrow(()->new BadRequestException("User con id " + id + " non trovato."));
}
public User putById(long id, UserRegistrationDTO userRegistrationDTO){
    userRepository.findById(id).orElseThrow(()->new BadRequestException("User con id " + id + " non trovato."));
    User user = userRepository.findById(id).get();

    user.setNome(userRegistrationDTO.nome());
    user.setCognome(userRegistrationDTO.cognome());
    user.setEmail(userRegistrationDTO.email());

    return userRepository.save(user);
}

public boolean deleteById(long id){
    try {
        userRepository.deleteById(id);
    return true;
    }catch (Exception e){
        return false;
    }
    }



public User findByEmail(String email){
    if(userRepository.findByEmail(email).isPresent()){
        return userRepository.findByEmail(email).get();
    }else{
        throw new BadRequestException("User con email " + email + " non trovato");
    }
}
}
