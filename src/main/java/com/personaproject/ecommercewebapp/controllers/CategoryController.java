package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.enums.ResponseMessages;
import com.personaproject.ecommercewebapp.enums.StandardServiceTokens;
import com.personaproject.ecommercewebapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final StandardServiceTokens standardServiceTokens;
    private final CategoryService categoryService;
//    private ResponseMessages responseMessages;

    @PostMapping("/store/create_category")
    public String createNewCategory(@RequestBody CategoryDTO categoryDTO,
                                    @RequestHeader String serviceToken,
                                    @RequestHeader String authToken) throws IllegalAccessException {
        boolean isServiceTokenSame = Objects.equals(serviceToken, standardServiceTokens.categoryToken);
        boolean isAuthTokenSame = Objects.equals(authToken, standardServiceTokens.authToken);

        System.out.println("\t\t |");
        System.out.println("\t\t |");
        System.out.println("check if service token is the same -> " + isServiceTokenSame);
        System.out.println("check if service auth is the same -> " + isAuthTokenSame);
        System.out.println("\t\t |");
        System.out.println("\t\t |");

        if (!isServiceTokenSame || !isAuthTokenSame) {
            throw new IllegalAccessException("Wrong Service and/or authentication Token");
        }
        return (categoryService.createCategory(categoryDTO));
    }
}
