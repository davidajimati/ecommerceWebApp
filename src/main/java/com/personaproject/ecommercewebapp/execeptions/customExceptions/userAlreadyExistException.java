package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class userAlreadyExistException extends RuntimeException {
    public userAlreadyExistException(String message) {
        super(message);
    }
}
