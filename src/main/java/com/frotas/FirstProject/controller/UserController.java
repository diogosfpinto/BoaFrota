package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.User;
import com.frotas.FirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/user")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewUser(@RequestBody User user){
        String pws = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(pws));
        userRepository.save(user);
        return "saved";
    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

}
