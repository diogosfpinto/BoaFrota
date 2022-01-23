package com.frotas.FirstProject.security;

import com.frotas.FirstProject.model.User;
import com.frotas.FirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User usuario = ur.findByEmail(login);

        if (usuario == null){
            throw new UsernameNotFoundException("Usuário Não Encontrado!");
        }
        return usuario;
    }
}
