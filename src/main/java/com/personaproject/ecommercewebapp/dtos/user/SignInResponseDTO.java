package com.personaproject.ecommercewebapp.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponseDTO {
    private String status;
    private String token;
}
