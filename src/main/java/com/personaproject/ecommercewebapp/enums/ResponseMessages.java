package com.personaproject.ecommercewebapp.enums;

public enum ResponseMessages {

    /**
     * 00 - header token incorrect
     * 1** - category creation
     * 2** - product addition
     */
    _202("202", "Unsuccessful", "Category creation unsuccessful");




    private final String code;
    private final String status;
    private final String message;

    private ResponseMessages(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
