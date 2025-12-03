package com.pisoft.pisoft.config;

import com.pisoft.pisoft.enums.Roles;
import com.pisoft.pisoft.enums.UserPermissions;
import com.pisoft.pisoft.filter.JwtAuthFilter;
import com.pisoft.pisoft.handler.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 1 step : we have to enable
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.formLogin(login ->
//                login.loginPage("newLogin.html")
//        );

        httpSecurity
                .authorizeHttpRequests(auth->

//                      auth.requestMatchers("/products/getAll").hasAnyRole("ADMIN" , "USER")
                        auth.requestMatchers("/products" , "auth/**" , "/login" ,  "/home.html").permitAll()
//                                .requestMatchers(HttpMethod.GET , "/posts/getAll").hasAnyRole(Roles.USER.name())
//                              .requestMatchers(HttpMethod.POST , "/posts/createPost").hasAnyRole(Roles.ADMIN.name() , Roles.CREATOR.name())
//                              .requestMatchers(HttpMethod.POST , "/posts/createPost").hasAuthority(UserPermissions.POST_CREATE.name())
                                .requestMatchers("/posts/createPost").access((authentication, object) ->

                                     new AuthorizationDecision(
                                            authentication.get().getAuthorities()
                                                    .stream()
                                                    .anyMatch(auths -> auths.getAuthority().equals("ROLE_"+Roles.ADMIN.name()))
                                            &&
                                                    authentication.get().getAuthorities()
                                                            .stream()
                                                            .anyMatch(auths1 -> auths1.getAuthority().equals(UserPermissions.POST_CREATE.name()))
                                             )
                                )
                        .anyRequest().authenticated()
                );

                        httpSecurity.csrf(csrf -> csrf.disable());
                        httpSecurity.sessionManagement(session ->
                                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                                .formLogin(login->login.disable())
                                .oauth2Login(login->login.failureUrl("/login?error=true")
                                        .successHandler(oAuth2SuccessHandler));
//                              httpSecurity.formLogin(form -> form.disable());
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
