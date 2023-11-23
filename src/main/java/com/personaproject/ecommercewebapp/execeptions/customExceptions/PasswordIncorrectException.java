package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class PasswordIncorrectException extends RuntimeException{
    public  PasswordIncorrectException(String message) {
        super(message);
    }
}
