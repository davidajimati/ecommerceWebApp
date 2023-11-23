package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class InvalidUserException extends  RuntimeException{
    public InvalidUserException(String message) {
        super(message);
    }
}
