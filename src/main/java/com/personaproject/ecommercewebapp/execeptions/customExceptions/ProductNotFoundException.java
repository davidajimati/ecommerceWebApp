package com.personaproject.ecommercewebapp.execeptions.customExceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
