package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.dtos.UserSignupDTO;
import com.personaproject.ecommercewebapp.services.HandleAuthentication;
import com.personaproject.ecommercewebapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final HandleAuthentication handleAuthentication;
    private final UserService userService;

    // sign up endpoint
    @PostMapping("/signup")
    ResponseEntity<?> createUser(@RequestHeader String authToken,
            @RequestHeader String serviceToken,
            @RequestBody UserSignupDTO userSignupDTO) {
        handleAuthentication.authenticateSignUp(authToken, serviceToken);
        return userService.createUser(userSignupDTO);
    }

    // sign in endpoint
}
