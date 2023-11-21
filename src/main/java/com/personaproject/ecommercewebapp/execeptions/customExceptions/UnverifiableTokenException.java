package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class UnverifiableTokenException extends RuntimeException {

    public UnverifiableTokenException(String message) {
        super(message);
    }
}
