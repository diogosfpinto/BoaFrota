package com.frotas.FirstProject.services;

import com.frotas.FirstProject.model.User;
import com.frotas.FirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addNewUser(User user){
        String pws = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(pws));

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

}
