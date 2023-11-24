package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.dtos.user.SignInDTO;
import com.personaproject.ecommercewebapp.dtos.user.SignInResponseDTO;
import com.personaproject.ecommercewebapp.dtos.user.SignupDTO;
import com.personaproject.ecommercewebapp.entity.AuthenticationToken;
import com.personaproject.ecommercewebapp.entity.User;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.InvalidUserException;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.PasswordIncorrectException;
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
    private final AuthenticationTokenService authenticationService;
    private final AuthenticationRepo authenticationRepo;


    public boolean checkIfUserExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    @SneakyThrows
    public ResponseEntity<?> createUser(SignupDTO payload) {
        if (checkIfUserExists(payload.getEmail())) {
            throw new userAlreadyExistException("User Already Exists, please login instead");
        }
        User user = new User(payload.getFirstName(), payload.getLastName(), payload.getEmail(), hashPassword(payload.getPassword()));
        userRepo.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveToken(authenticationToken);

        return ResponseEntity.status(HttpStatus.CREATED).body("User '" + payload.getFirstName() + "' created successfully");
    }

    @SneakyThrows
    public SignInResponseDTO handleSignIn(SignInDTO payload) throws PasswordIncorrectException, InvalidUserException {
        // find user via email to be sure it exists
        if (!checkIfUserExists(payload.getEmail()))
            throw new InvalidUserException("Incorrect credentials, ensure correctness and try again.");

        User user = userRepo.findByEmail(payload.getEmail());
        String token = "";
        // hash the password and compare if it's equal
        if (hashPassword(payload.getPassword()).equals(user.getPassword())) {
            // retrieve the token
            token = authenticationRepo.findByUser(user).getToken();
        } else throw new PasswordIncorrectException("Incorrect credentials, ensure correctness and try again.");
        // return the response
        return new SignInResponseDTO("success", token);
    }
}
