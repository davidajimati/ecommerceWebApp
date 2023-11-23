package com.personaproject.ecommercewebapp.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignInDTO {
    private String email;
    private String password;
}
