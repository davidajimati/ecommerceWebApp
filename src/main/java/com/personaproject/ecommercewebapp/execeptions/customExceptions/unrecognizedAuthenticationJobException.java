package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class unrecognizedAuthenticationJobException extends RuntimeException{
    public unrecognizedAuthenticationJobException(String message) {
        super(message);
    }
}
