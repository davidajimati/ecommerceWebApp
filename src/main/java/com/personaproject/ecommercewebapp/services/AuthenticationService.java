package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.entity.AuthenticationToken;
import com.personaproject.ecommercewebapp.repository.AuthenticationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationRepo authenticationRepo;

    public void saveToken(AuthenticationToken authenticationToken) {
        authenticationRepo.save(authenticationToken);
    }
}
