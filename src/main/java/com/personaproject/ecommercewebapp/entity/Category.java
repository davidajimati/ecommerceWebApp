package com.personaproject.ecommercewebapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private @NotBlank String categoryName;
    private @NotBlank String categoryDescription;
    private @NotBlank String categoryImageUrl;

    public Category(String categoryName, String categoryDescription, String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryImageUrl = categoryImageUrl;
    }

    public Category() {
    }
}
