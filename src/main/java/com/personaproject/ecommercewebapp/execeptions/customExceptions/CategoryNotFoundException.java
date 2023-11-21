package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class CategoryNotFoundException extends RuntimeException{

    public  CategoryNotFoundException(String message) {
        super(message);
    }
}
