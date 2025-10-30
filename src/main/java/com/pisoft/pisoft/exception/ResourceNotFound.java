package com.pisoft.pisoft.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String name){
        super(name);
    }

}
