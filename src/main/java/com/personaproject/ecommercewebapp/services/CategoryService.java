package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.repository.CategoryRepo;

public class CategoryService {

    CategoryRepo categoryRepo;

    public String createCategory(CategoryDTO categoryDTO) {
        try {

            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setCategoryDescription(categoryDTO.getDescription());
            category.setCategoryImageUrl(categoryDTO.getImageUrl());

            categoryRepo.save(category);
            return (category.getCategoryName() + " Category created successfully!");
        } catch (Exception e) {
            return ("an error occured");
        }
    }
}
