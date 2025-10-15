package com.pisoft.pisoft;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env" , havingValue = "production")
public class ProductionDatabase implements DB {

    public String getDatabase(){
        return "Production Database";
    }
}
