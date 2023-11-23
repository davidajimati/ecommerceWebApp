package com.personaproject.ecommercewebapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignupDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
