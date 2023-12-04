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
    private @NotNull String imageUrl;
    private double price;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    Category category;

    public Product(String productName, String productDescription, String imageUrl, Double price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product() {
    }
}
