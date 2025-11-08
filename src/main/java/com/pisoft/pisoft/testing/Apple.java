package com.pisoft.pisoft.testing;

import org.springframework.stereotype.Component;

@Component
public class Apple {



//    @Autowired
//    Mango mango;


    Mango mango;

    public Apple(Mango mango){
        this.mango = mango;
    }


    void display(){
//        mango.eat();
    }


}
