package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.globalVariables.StandardServiceTokens;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HandleAuthentication {
    private final StandardServiceTokens standardServiceTokens;

    public boolean authenticateCategoryCreation(String authToken, String serviceToken) {

        return Objects.equals(serviceToken, standardServiceTokens.categoryToken)
                || Objects.equals(authToken, standardServiceTokens.authToken);
    }
}
