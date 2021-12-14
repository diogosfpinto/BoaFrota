package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.User;
import com.frotas.FirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String addNewUser(@RequestBody User user){

        userRepository.save(user);
        return "saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

}
