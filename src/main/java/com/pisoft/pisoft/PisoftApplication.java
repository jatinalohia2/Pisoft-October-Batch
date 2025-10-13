package com.pisoft.pisoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PisoftApplication implements CommandLineRunner {

    @Autowired
    Apple apple ;

	public static void main(String[] args) {
		SpringApplication.run(PisoftApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        apple.eat();
        apple.see();

    }
}
