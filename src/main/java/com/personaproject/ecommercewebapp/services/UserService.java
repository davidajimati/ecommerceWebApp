package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.dtos.UserSignupDTO;
import com.personaproject.ecommercewebapp.entity.User;
import com.personaproject.ecommercewebapp.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public ResponseEntity<?> createUser(UserSignupDTO userSignupDTO) {
        User user = new User();

        user.setEmail(userSignupDTO.getEmail());
        user.setPassword(userSignupDTO.getPassword());
        user.setFirstName(userSignupDTO.getFirstName());
        user.setLastName(userSignupDTO.getLastName());
        user.setPassword(userSignupDTO.getPassword());
        userRepo.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User '" + userSignupDTO.getFirstName() + "' created successfully");
    }
}
