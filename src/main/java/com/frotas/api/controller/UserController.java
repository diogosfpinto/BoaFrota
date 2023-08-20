package com.frotas.api.controller;

import com.frotas.api.model.User;
import com.frotas.api.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("Criando novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário criado com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao criar novo usuário, verifique as informações.")
    })
    @PostMapping(path="/user")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User addNewUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    @ApiOperation("Retorna todos usuários cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuários retornados com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao retornar todos os usuários, verifique as informações.")
    })
    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUser(){
        return userService.getAllUsers();
    }

    @ApiOperation("Retorna usuário pelo ID informado como parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário retornado com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao retornar o usuário por ID, verifique as informações.")
    })
    @GetMapping(path="/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable (value = "id") Integer id){
        return userService.getUserById(id);
    }

    @ApiOperation("Atualizando dados do usuário por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário atualizado com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao atualizar Usuário, verifique as informações.")
    })
    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @ApiOperation("Excluindo um usuário do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário excluído com sucesso."),
            @ApiResponse(code = 500,
                    message = "Houve um erro ao excluir o usuário, verifique as informações.")
    })
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteVUserbyId(@PathVariable (value = "id") Integer id){
        return userService.deleteUserbyId(id);
    }
}
