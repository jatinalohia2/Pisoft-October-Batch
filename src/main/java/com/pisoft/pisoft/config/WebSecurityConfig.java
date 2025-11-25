package com.pisoft.pisoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 1 step : we have to enable
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.formLogin(login ->
//                login.loginPage("newLogin.html")
//        );

        httpSecurity.formLogin(Customizer.withDefaults())

                .authorizeHttpRequests(auth->

                        auth.requestMatchers("/products/getAll").hasAnyRole("ADMIN" , "USER")
                            .requestMatchers("/products/**").permitAll()
                                .anyRequest().authenticated());

                        httpSecurity.csrf(csrf -> csrf.disable());

                        httpSecurity.sessionManagement(session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                );

//                        httpSecurity.formLogin(form -> form.disable());

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService inMemoryDatabaseUser(){

        UserDetails normalUser = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();


        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser , admin);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
