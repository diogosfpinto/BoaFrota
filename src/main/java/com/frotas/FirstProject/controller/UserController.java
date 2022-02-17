package com.frotas.FirstProject.controller;

import com.frotas.FirstProject.model.User;
import com.frotas.FirstProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/user")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User addNewUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping(path="/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable (value = "id") Integer id){
        return userService.getUserById(id);
    }

}
