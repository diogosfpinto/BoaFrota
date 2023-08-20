package com.frotas.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.POST,"/user").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/{id}").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.formLogin();
        http.httpBasic();
    }

    /**
     * @param auth Realiza a autenticação de login informando ao objeto de gerenciamento de autenticação
     * o classe que realiza a consulta de usuários no banco
     *
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //Autenticação com usuário em banco de dados
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);

        //Métodos de Autenticação em memória
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("developer")
                .password(encoder.encode("developer"))
                .roles("ADMIN");

        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("client")
                .password(encoder.encode("client"))
                .roles("USER");
    }
}
