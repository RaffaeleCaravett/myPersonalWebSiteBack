package com.example.myPersonalApp.user;

import com.example.myPersonalApp.payloads.entities.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/{id}")
    public User getById(@PathVariable long id){
        return userService.findById(id);
    }
    @PutMapping("/me")
    public User putById(@AuthenticationPrincipal User currentUser, @RequestBody UserRegistrationDTO userRegistrationDTO){
        return userService.putById(currentUser.getId(),userRegistrationDTO);
    }

    @DeleteMapping("/me")
    public boolean deleteById(@AuthenticationPrincipal User currentUser){
        return userService.deleteById(currentUser.getId());
    }
}
