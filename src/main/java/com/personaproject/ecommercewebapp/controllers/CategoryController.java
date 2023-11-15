package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.enums.ResponseMessages;
import com.personaproject.ecommercewebapp.enums.StandardServiceTokens;
import com.personaproject.ecommercewebapp.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CategoryController {

    StandardServiceTokens standardServiceTokens;
    private CategoryService categoryService;
    private ResponseMessages responseMessages;

    @PostMapping("/store/create_category")
    public String createNewCategory(@RequestBody CategoryDTO categoryDTO,
                                    @RequestHeader String serviceToken,
                                    @RequestHeader String authToken) throws IllegalAccessException {
        if (!Objects.equals(serviceToken, StandardServiceTokens.getToken()) ||
                !Objects.equals(authToken, StandardServiceTokens.getAuth())) {
            throw new IllegalAccessException("Wrong Service and/or authentication Token");
        }
        return (categoryService.createCategory(categoryDTO));
    }
}
