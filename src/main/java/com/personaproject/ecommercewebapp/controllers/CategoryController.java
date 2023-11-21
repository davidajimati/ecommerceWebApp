package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.services.CategoryService;
import com.personaproject.ecommercewebapp.services.HandleAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final HandleAuthentication handleAuthentication;
    private final ResponseServices responseServices;

    @PostMapping("/create")
    public ResponseEntity<?> createNewCategory(@RequestBody CategoryDTO categoryDTO,
                                               @RequestHeader String serviceToken, @RequestHeader String authToken) throws AuthenticationException {
        handleAuthentication.authenticateCategoryJob(authToken, serviceToken);
        return categoryService.createCategory(categoryDTO);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> categoryList() {
        return new ResponseEntity<>(categoryService.listAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/get_all_dev")
    public ResponseEntity<?> devCategoryList() {
        return new ResponseEntity<>(categoryService.devListAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/get/{categoryId}")
    public CategoryDTO getById(@PathVariable Long categoryId) {
        return categoryService.findByID(categoryId);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId,
                                 @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, categoryDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId,
                                 @RequestHeader String serviceToken,
                                 @RequestHeader String authToken) {
        handleAuthentication.authenticateCategoryJob(authToken, serviceToken);
        return new ResponseEntity<>(categoryService.removeCategory(categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteAllCategory(@RequestHeader String authToken, @RequestHeader String serviceToken) throws AuthenticationException {
        handleAuthentication.authenticateCategoryJob(authToken, serviceToken);
        return new ResponseEntity<>(categoryService.removeAllCategory(), HttpStatus.OK);
    }
}
