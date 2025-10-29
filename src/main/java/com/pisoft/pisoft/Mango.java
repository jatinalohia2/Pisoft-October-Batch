package com.pisoft.pisoft;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Mango {

    void eat(){
        System.out.println("eating mango");
    }

    @PostConstruct
    void invokeBefore(){
        System.out.println("starts first...... ");
    }

    @PreDestroy
    void invokeAfter(){
        System.out.println("end...............");
    }

}
