package com.personaproject.ecommercewebapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "category_name")
    private @NotBlank String categoryName;

    @Column(name = "description")
    private @NotBlank String  categoryDescription;

    @Column(name = "image_rl")
    private @NotBlank String categoryImageUrl;

    public Category(String categoryName, String categoryDescription, String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryImageUrl = categoryImageUrl;
    }

    public Category() {}
}
