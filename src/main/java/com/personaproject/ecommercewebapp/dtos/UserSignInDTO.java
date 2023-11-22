package com.personaproject.ecommercewebapp.dtos;

import lombok.Data;

@Data
public class UserSignInDTO {
    private String email;
    private String password;
}
