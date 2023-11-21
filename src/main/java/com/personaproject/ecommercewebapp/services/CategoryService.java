package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.CategoryNotFoundException;
import com.personaproject.ecommercewebapp.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ResponseServices responseServices;
    private final CategoryRepo categoryRepo;

    public ResponseEntity<?> createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryDescription(categoryDTO.getDescription());
        category.setCategoryImageUrl(categoryDTO.getImageUrl());

        categoryRepo.save(category);
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.CREATED,
                "Category created successfully"), HttpStatus.CREATED);
    }

    private CategoryDTO getCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setDescription(category.getCategoryDescription());
        categoryDTO.setImageUrl(category.getCategoryImageUrl());
        return categoryDTO;
    }

    public void checkIfCategoryExists(Long categoryId) {
        if (findByID(categoryId) == null)
            throw new CategoryNotFoundException("Category with ID " + categoryId + " Cannot be found");
    }

    public List<CategoryDTO> listAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        ArrayList<CategoryDTO> allCategories = new ArrayList<>();
        for (Category category : categories) allCategories.add(getCategoryDTO(category));
        return allCategories;
    }

    public CategoryDTO findByID(Long categoryId) {
        if (categoryRepo.findById(categoryId).isEmpty())
            throw new CategoryNotFoundException("Category with ID " + categoryId + " Cannot be found");
        return getCategoryDTO(categoryRepo.findById(categoryId).get());
    }

    public ResponseEntity<?> updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        if (categoryRepo.findById(categoryId).isEmpty())
            throw new CategoryNotFoundException("Category with ID " + categoryId + " Cannot be found");

        Category categoryItem = categoryRepo.getReferenceById(categoryId);
        categoryItem.setCategoryDescription(categoryDTO.getDescription());
        categoryItem.setCategoryName(categoryDTO.getCategoryName());
        categoryItem.setCategoryImageUrl(categoryDTO.getImageUrl());
        categoryRepo.save(categoryItem);
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.ACCEPTED,
                "Category with ID " + categoryId + " updated!"), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> removeCategory(Long categoryId) throws CategoryNotFoundException {
        if (categoryRepo.findById(categoryId).isEmpty())
            throw new CategoryNotFoundException("Category with ID " + categoryId + " Cannot be found");

        categoryRepo.deleteById(categoryId);
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.OK,
                "Category with ID " + categoryId + " deleted!"), HttpStatus.OK);
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
