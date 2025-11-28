package com.pisoft.pisoft.config;

import com.pisoft.pisoft.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 1 step : we have to enable
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.formLogin(login ->
//                login.loginPage("newLogin.html")
//        );

        httpSecurity.formLogin(Customizer.withDefaults())

                .authorizeHttpRequests(auth->

//                        auth.requestMatchers("/products/getAll").hasAnyRole("ADMIN" , "USER")
                                auth .requestMatchers("/products" , "auth/**").permitAll()
                                .anyRequest().authenticated()
                );

                        httpSecurity.csrf(csrf -> csrf.disable());

                        httpSecurity.sessionManagement(session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                                .formLogin(login->login.disable());
//                        httpSecurity.formLogin(form -> form.disable());

        return httpSecurity.build();
    }

//    @Bean
//    UserDetailsService inMemoryDatabaseUser(){
//
//        UserDetails normalUser = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser , admin);
//
//    }




    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception {
     return  configuration.getAuthenticationManager();
    }
}
