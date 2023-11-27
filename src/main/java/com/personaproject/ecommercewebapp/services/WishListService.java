package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.entity.AuthenticationToken;
import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.entity.User;
import com.personaproject.ecommercewebapp.entity.WishList;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.WrongTokenException;
import com.personaproject.ecommercewebapp.repository.AuthenticationRepo;
import com.personaproject.ecommercewebapp.repository.WishListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepo wishListRepo;
    private final AuthenticationRepo authenticationRepo;

    public ResponseEntity<?> saveToWishList(Product product, String token) throws WrongTokenException {
        // verification
        User user = verifyTokenAndGetUser(token);

        // create(initiate) the target wishlist and save
        createWishList(new WishList(user, product));

        return ResponseEntity.status(HttpStatus.CREATED).body("product added to wishlist successfully");
    }

//    public ResponseEntity<?> getWishListItems() {
//    }

    public User verifyTokenAndGetUser(String token) {
        // authenticate the token
        if (Objects.isNull(token)) {
            throw new WrongTokenException("Token not present ");
        }
        // get the user
        AuthenticationToken authenticationToken = authenticationRepo.findByToken(token);
        User user = authenticationToken.getUser();

        if (!authenticationToken.getToken().equals(token)) throw new WrongTokenException("Invalid Token!");
        return user;
    }

    public void createWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }
}
