package com.capitalnumber.demo.exceptions;

public class ResourceUnavailableException extends DemoException{

    public ResourceUnavailableException(){
        super();
    }

    public ResourceUnavailableException(String message){
        super(message);
    }
}
