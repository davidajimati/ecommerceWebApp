package com.personaproject.ecommercewebapp.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignupDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
