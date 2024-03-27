package com.example.myPersonalApp.auth;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.*;
import com.example.myPersonalApp.richiesteTalk.RichiesteTalk;
import com.example.myPersonalApp.richiesteTalk.RichiesteTalkService;
import com.example.myPersonalApp.security.JWTTools;
import com.example.myPersonalApp.talk.Talk;
import com.example.myPersonalApp.talk.TalkService;
import com.example.myPersonalApp.user.User;
import com.example.myPersonalApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private RichiesteTalkService richiesteTalkService;
@Autowired
private TalkService talkService;

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
            if(body.email().equals("raffaelecaravetta13@gmail.com")){
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }else {
                throw new BadRequestException("Abbiamo già un admin per questo ufficio, ci dispiace.");
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
    @PostMapping("/richiesteTalk")
    public RichiesteTalk save (@RequestBody @Validated RichiesteTalkDTO richiesteTalkDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return richiesteTalkService.save(richiesteTalkDTO);
    }
    @GetMapping("/talk")
    public Page<Talk> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "id") String orderBy){
        return talkService.getAllPaginated(page,size,orderBy);
    }

    @GetMapping("/talk/categoria/{categoria}")
    public  Page<Talk> getAllByCategoria(@PathVariable String categoria,
                                         @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "id") String orderBy){
        Categoria categoria1 = Categoria.valueOf(categoria);
        return talkService.findByCategoria(categoria1,page,size,orderBy);
    }
    @GetMapping("/talk/titolo/{titolo}")
    public  Page<Talk> getAllByTitolo(@PathVariable String titolo,
                                      @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "id") String orderBy){
        return talkService.findByTitolo(titolo,page,size,orderBy);
    }
    @GetMapping("/talk/titoloAndCategoria/{titolo}/{categoria}")
    public  Page<Talk> getAllByTitoloAndCategoria(@PathVariable String titolo,@PathVariable String categoria,
                                                  @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "id") String orderBy){
        Categoria categoria1 = Categoria.valueOf(categoria);
        return talkService.findByTitoloAndCategoria(titolo,categoria1,page,size,orderBy);
    }
}