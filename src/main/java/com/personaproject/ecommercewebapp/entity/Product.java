package com.personaproject.ecommercewebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private @NotNull String productName;
    private @NotNull String productDescription;
    private @NotNull long categoryRefId;
    private @NotNull String imageUrl;


//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "category_id")
//    Category category;


    private Double price;

    public Product(String productName, String productDescription, long categoryRefId, String imageUrl, Double price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryRefId = categoryRefId;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product() {
    }
}
