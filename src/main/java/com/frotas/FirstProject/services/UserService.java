package com.frotas.FirstProject.services;

import com.frotas.FirstProject.model.User;
import com.frotas.FirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User addNewUser(User user){
        String pws = user.getPassword();
        user.setPassword(passwordEncoder.encode(pws));

        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * @param id id
     * @return User by Id
     */
    public ResponseEntity<User> getUserById(Integer id){
        return userRepository.findById(id).map(
                        user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<User> updateUser(User user){

        Integer id = getUserAuthenticated().getId();

        return userRepository.findById(id).map(
                userToUpdate -> {
                    userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
                    userToUpdate.setEmail(user.getEmail());
                    userToUpdate.setName(user.getName());
                    User updated = userRepository.save(userToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    private User getUserAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){

            return (User) authentication.getPrincipal();
        }
        return new User();
    }
}
