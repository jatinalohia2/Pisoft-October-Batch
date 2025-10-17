package com.pisoft.pisoft;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env" , havingValue = "static")
public class StaticDatabase implements DB{
    @Override
    public String getDatabase() {
        return "Static Database";
    }
}
