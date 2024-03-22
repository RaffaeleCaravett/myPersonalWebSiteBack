package com.example.myPersonalApp.auth;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.Token;
import com.example.myPersonalApp.payloads.entities.UserLoginDTO;
import com.example.myPersonalApp.payloads.entities.UserLoginSuccessDTO;
import com.example.myPersonalApp.payloads.entities.UserRegistrationDTO;
import com.example.myPersonalApp.security.JWTTools;
import com.example.myPersonalApp.user.User;
import com.example.myPersonalApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService utenteService;



    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body) throws Exception {

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody @Validated UserRegistrationDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{token}")
    @ResponseStatus(HttpStatus.OK)
    public User verifyToken(@PathVariable String token){
        return jwtTools.verifyToken(token);
    }
    @GetMapping("/refreshToken/{refreshToken}")
    @ResponseStatus(HttpStatus.OK)
    public Token verifyRefreshToken(@PathVariable String refreshToken){
        return jwtTools.verifyRefreshToken(refreshToken);
    }

@GetMapping("/categorie")
public List<Categoria> getAllCategories(){
        List<Categoria> categorias =new ArrayList<>();
        categorias.add(Categoria.Fitness);
        categorias.add(Categoria.Business);
        categorias.add(Categoria.Career);
        categorias.add(Categoria.Work);
        categorias.add(Categoria.Travels);
        categorias.add(Categoria.Health);

        return categorias;
}
}