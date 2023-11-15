package com.personaproject.ecommercewebapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "category_name")
    private @NotBlank String categoryName;

    @Column(name = "description")
    private @NotBlank String  categoryDescription;

    @Column(name = "image_rl")
    private @NotBlank String categoryImageUrl;
}
