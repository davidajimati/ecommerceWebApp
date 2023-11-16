package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.dtos.CategoryDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
