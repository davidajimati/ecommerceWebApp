package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.repository.WishListRepo;
import com.personaproject.ecommercewebapp.services.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListRepo wishListRepo;
    private final WishListService wishListService;

    //save product to wishlist
    @PostMapping("/add/{token}")
    public ResponseEntity<?> addToWishList(@RequestBody Product product,
                                           @PathVariable String token) {
       return wishListService.saveToWishList(product, token);
    }

    // retrieve all customers' wislist Items
}
