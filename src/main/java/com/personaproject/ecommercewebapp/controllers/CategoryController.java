package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.enums.StandardServiceTokens;
import com.personaproject.ecommercewebapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final StandardServiceTokens standardServiceTokens;
    private final CategoryService categoryService;
//    private ResponseMessages responseMessages;

    @PostMapping("/create")
    public String createNewCategory(@RequestBody CategoryDTO categoryDTO,
                                    @RequestHeader String serviceToken,
                                    @RequestHeader String authToken) throws IllegalAccessException {
        System.out.println("\t\t |");
        System.out.println("\t\t |");

        if (!Objects.equals(serviceToken, standardServiceTokens.categoryToken)
                || !Objects.equals(authToken, standardServiceTokens.authToken)) {
            throw new IllegalAccessException("Wrong Service and/or authentication Token");
        }
        return (categoryService.createCategory(categoryDTO));
    }

    @GetMapping("/get_all")
    public List<Category> categoryList() {
        return categoryService.listAllCategories();
    }

    @PostMapping("/update/{categoryId}")
    public String updateCategory(@PathVariable Long categoryId,
                                 @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryId, categoryDTO);
    }
}
