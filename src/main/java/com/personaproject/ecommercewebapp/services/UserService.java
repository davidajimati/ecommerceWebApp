package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.dtos.UserSignupDTO;
import com.personaproject.ecommercewebapp.entity.AuthenticationToken;
import com.personaproject.ecommercewebapp.entity.User;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.userAlreadyExistException;
import com.personaproject.ecommercewebapp.repository.AuthenticationRepo;
import com.personaproject.ecommercewebapp.repository.UserRepo;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserService {
    /**
     * check if user already exists
     * hash password
     * create the new user
     * create the authentication token
     * save authentication token
     */

    private final UserRepo userRepo;
    private final AuthenticationService authenticationService;


    public void checkIfUserExists(String email) throws userAlreadyExistException {
        if (userRepo.findByEmail(email) != null) throw new userAlreadyExistException("User already exists");
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    @SneakyThrows
    public ResponseEntity<?> createUser(UserSignupDTO payload) {
        checkIfUserExists(payload.getEmail());
        User user = new User(payload.getFirstName(), payload.getLastName(), payload.getEmail(), hashPassword(payload.getPassword()));
        userRepo.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveToken(authenticationToken);

        return ResponseEntity.status(HttpStatus.CREATED).body("User '" + payload.getFirstName() + "' created successfully");
    }
}
