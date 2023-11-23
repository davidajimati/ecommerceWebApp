package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.execeptions.customExceptions.UnverifiableTokenException;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.unrecognizedAuthenticationJobException;
import com.personaproject.ecommercewebapp.globalVariables.StandardServiceTokens;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HandleAuthentication {
    private final StandardServiceTokens standardServiceTokens;

    public boolean authenticateCategoryJob(String authToken, String serviceToken) throws UnverifiableTokenException {

        if (!Objects.equals(serviceToken, standardServiceTokens.categoryToken) ||
                !Objects.equals(authToken, standardServiceTokens.authToken))
            throw new UnverifiableTokenException("Header token(s) cannot be authenticated");
        return true;
    }
    public boolean authenticateProductJob(String authToken, String serviceToken) {
        if (!Objects.equals(serviceToken, standardServiceTokens.productToken) ||
                !Objects.equals(authToken, standardServiceTokens.authToken))
            throw new UnverifiableTokenException("Header token(s) cannot be authenticated");
        return true;
    }

    public void authenticateSignUp(String authToken, String serviceToken) throws unrecognizedAuthenticationJobException {
        if (!Objects.equals(serviceToken, standardServiceTokens.signUpToken) ||
                !Objects.equals(authToken, standardServiceTokens.authToken))
            throw new UnverifiableTokenException("Header token(s) cannot be authenticated");
    }
}
