package com.capitalnumber.demo.exceptions;

public class DemoException extends RuntimeException{
    public DemoException(){
        super();
    }

    public DemoException(String message){
        super(message);
    }
}
