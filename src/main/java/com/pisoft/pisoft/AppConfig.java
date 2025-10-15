package com.pisoft.pisoft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Apple getApple(){
        return new Apple();
    }   

    @Bean
    public Kiwi getKiwi(){
        return new Kiwi();
    }



}
