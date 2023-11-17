package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ResponseServices responseServices;
    private final CategoryRepo categoryRepo;

    public HashMap<String, Object> createCategory(CategoryDTO categoryDTO) {
        try {
            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setCategoryDescription(categoryDTO.getDescription());
            category.setCategoryImageUrl(categoryDTO.getImageUrl());

            categoryRepo.save(category);
            return responseServices.apiResponse(HttpStatus.CREATED, true, "Category created successfully");
        } catch (Exception e) {
            return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "category not created");
        }
    }

    public List<Category> listAllCategories() {
        return categoryRepo.findAll();
    }

    public Object updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        try {
            Category categoryItem = categoryRepo.getReferenceById(categoryId);
            categoryItem.setCategoryDescription(categoryDTO.getDescription());
            categoryItem.setCategoryName(categoryDTO.getCategoryName());
            categoryItem.setCategoryImageUrl(categoryDTO.getImageUrl());
            categoryRepo.save(categoryItem);
            return responseServices.apiResponse(HttpStatus.ACCEPTED, true,
                    "Category with ID " + categoryId + " updated!");
        } catch (Exception e) {
            return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "Category cannot be updated");
        }
    }

    public Object removeCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
        return responseServices.apiResponse(HttpStatus.OK, true, "Category with ID " + categoryId + " deleted!");
    }

    public Object removeAllCategory() {
        categoryRepo.deleteAll();
        return responseServices.apiResponse(HttpStatus.OK, true, "All categories deleted");
    }
}
