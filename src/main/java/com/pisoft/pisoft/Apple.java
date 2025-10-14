package com.pisoft.pisoft;

import org.springframework.beans.factory.annotation.Autowired;

public class Apple {

    @Autowired
    Mango mango;

    void display(){
        mango.eat();
    }


}
