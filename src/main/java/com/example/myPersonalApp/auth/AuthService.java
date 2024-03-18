package com.example.myPersonalApp.auth;

import com.example.myPersonalApp.enums.Role;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.exceptions.UnauthorizedException;
import com.example.myPersonalApp.payloads.entities.Token;
import com.example.myPersonalApp.payloads.entities.UserLoginDTO;
import com.example.myPersonalApp.payloads.entities.UserRegistrationDTO;
import com.example.myPersonalApp.security.JWTTools;
import com.example.myPersonalApp.user.User;
import com.example.myPersonalApp.user.UserRepository;
import com.example.myPersonalApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;


    public Token authenticateUser(UserLoginDTO body) throws Exception {
        // 1. Verifichiamo che l'email dell'utente sia nel db
        User user = usersService.findByEmail(body.email());
        // 2. In caso affermativo, verifichiamo se la password corrisponde a quella trovata nel db
        if(bcrypt.matches(body.password(), user.getPassword()))  {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }


    public User registerUser(UserRegistrationDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        userRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        User newUser = new User();
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setRole(Role.ADMIN);
        userRepository.save(newUser);

        return newUser;
    }



}