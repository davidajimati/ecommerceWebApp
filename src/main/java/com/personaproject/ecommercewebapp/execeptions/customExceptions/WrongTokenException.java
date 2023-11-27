package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class WrongTokenException extends RuntimeException {
    public WrongTokenException(String message) {
        super(message);
    }
}
