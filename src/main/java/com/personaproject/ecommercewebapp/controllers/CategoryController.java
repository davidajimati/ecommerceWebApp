package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.services.CategoryService;
import com.personaproject.ecommercewebapp.services.HandleAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final HandleAuthentication handleAuthentication;
    private final ResponseServices responseServices;

    @PostMapping("/create")
    public Object createNewCategory(@RequestBody CategoryDTO categoryDTO,
                                    @RequestHeader String serviceToken,
                                    @RequestHeader String authToken) {
        if (handleAuthentication.authenticateCategoryJob(authToken, serviceToken))
            return categoryService.createCategory(categoryDTO);
        return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "Token(s) cannot be validated");
    }

    @GetMapping("/get_all")
    public List<Category> categoryList() {
        return categoryService.listAllCategories();
    }

    @GetMapping("/get/{categoryId}")
    public Optional<Category> getById(@PathVariable Long categoryId) {
        return categoryService.findByID(categoryId);
    }

    @PutMapping("/update/{categoryId}")
    public Object updateCategory(@PathVariable Long categoryId,
                                 @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryId, categoryDTO);
    }

    @DeleteMapping("/delete/{categoryId}")
    public Object deleteCategory(@PathVariable Long categoryId,
                                 @RequestHeader String serviceToken,
                                 @RequestHeader String authToken) {
        try {
            if (handleAuthentication.authenticateProductJob(authToken, serviceToken))
                return (categoryService.removeCategory(categoryId));
            return responseServices.apiResponse(HttpStatus.BAD_REQUEST,
                    false, "Category with ID " + categoryId + " Cannot be deleted");
        } catch (Exception e) {
            return ("Token(s) cannot be verified");
        }
    }

    @DeleteMapping("/delete_all")
    public Object deleteAllCategory(@RequestHeader String authToken,
                                    @RequestHeader String serviceToken) {
        if (handleAuthentication.authenticateCategoryJob(authToken, serviceToken))
            return categoryService.removeAllCategory();
        return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "Token(s) cannot be validated");
    }
}
