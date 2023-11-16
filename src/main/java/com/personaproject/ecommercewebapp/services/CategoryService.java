package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.repository.CategoryRepo;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepo categoryRepo;

    public String createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryDescription(categoryDTO.getDescription());
        category.setCategoryImageUrl(categoryDTO.getImageUrl());

        categoryRepo.save(category);
        return (category.getCategoryName() + " Category created successfully!");
    }

    public List<Category> listAllCategories() {
        return categoryRepo.findAll();
    }

    public String updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category categoryItem = categoryRepo.getReferenceById(categoryId);
        categoryItem.setCategoryDescription(categoryDTO.getDescription());
        categoryItem.setCategoryName(categoryDTO.getCategoryName());
        categoryItem.setCategoryImageUrl(categoryDTO.getImageUrl());
        categoryRepo.save(categoryItem);
        return ("Category with ID " + categoryId + " updated!");
    }

    public String removeCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
//        categoryRepo.save(categoryItem);
        return ("Category with ID " + categoryId + " deleted!");
    }
}
