package com.personaproject.ecommercewebapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignInDTO {
    private String email;
    private String password;
}
