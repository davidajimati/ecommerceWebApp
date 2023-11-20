package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ResponseServices responseServices;
    private final CategoryRepo categoryRepo;

    public ResponseEntity<?> createCategory(CategoryDTO categoryDTO) {
        try {
            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setCategoryDescription(categoryDTO.getDescription());
            category.setCategoryImageUrl(categoryDTO.getImageUrl());

            categoryRepo.save(category);
            return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.CREATED, "Category created successfully"), HttpStatus.CREATED);
//            return responseServices.apiResponse(HttpStatus.CREATED, true, "Category created successfully");
        } catch (Exception e) {
//            return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "category not created");
            return new ResponseEntity<>("Category not created", HttpStatus.BAD_REQUEST);
        }
    }

    private CategoryDTO getCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setDescription(category.getCategoryDescription());
        categoryDTO.setImageUrl(category.getCategoryImageUrl());
        return categoryDTO;
    }

    public List<CategoryDTO> listAllCategories() {
        List<Category> categories = categoryRepo.findAll();

        ArrayList<CategoryDTO> allCategories = new ArrayList<>();

        for (Category category : categories) allCategories.add(getCategoryDTO(category));
        return allCategories;
    }

    public CategoryDTO findByID(Long categoryId) {
        CategoryDTO category = null;
        Optional<Category> dbCategory = categoryRepo.findById(categoryId);
        if(dbCategory.isPresent()) {
             category = getCategoryDTO(dbCategory.get());
        }
        return category;
    }

    public ResponseEntity<?> updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        try {
            Category categoryItem = categoryRepo.getReferenceById(categoryId);
            categoryItem.setCategoryDescription(categoryDTO.getDescription());
            categoryItem.setCategoryName(categoryDTO.getCategoryName());
            categoryItem.setCategoryImageUrl(categoryDTO.getImageUrl());
            categoryRepo.save(categoryItem);
            return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.ACCEPTED,
                    "Category with ID " + categoryId + " updated!"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.BAD_REQUEST,
                    "Category cannot be updated"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> removeCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.OK,
                "Category with ID " + categoryId + " deleted!"), HttpStatus.BAD_REQUEST);
    }

    public Object removeAllCategory() {
        categoryRepo.deleteAll();
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.OK,
                "All categories deleted"), HttpStatus.OK);
    }

    public List<Category> devListAllCategories() {
        return categoryRepo.findAll();
    }
}
