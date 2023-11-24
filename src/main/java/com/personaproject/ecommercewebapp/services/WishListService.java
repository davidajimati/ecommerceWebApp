package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.entity.User;
import com.personaproject.ecommercewebapp.repository.AuthenticationRepo;
import com.personaproject.ecommercewebapp.repository.UserRepo;
import com.personaproject.ecommercewebapp.repository.WishListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepo wishListRepo;
    private final AuthenticationRepo authenticationRepo;

    public ResponseEntity<?> saveToWishList(Product product, String token) {
        // authenticate the token
        User user = authenticationRepo.findByToken()

        // find the user

        // save item in the wishlist
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("product added to wishlist successfully");
    }
}
