package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.entity.AuthenticationToken;
import com.personaproject.ecommercewebapp.entity.User;
import com.personaproject.ecommercewebapp.repository.AuthenticationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationTokenService {
    private final AuthenticationRepo authenticationRepo;

    public void saveToken(AuthenticationToken authenticationToken) {
        authenticationRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return authenticationRepo.findByUser(user);
    }
}
