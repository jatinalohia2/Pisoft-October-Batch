package com.pisoft.pisoft;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env" , havingValue = "development")
public class DevelopmentDB implements DB{

    @Override
    public String getDatabase(){
        return "Development Database";
    }

}
