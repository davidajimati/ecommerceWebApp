package com.personaproject.ecommercewebapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "image_url")
    private String imageUrl;

    private Double price;

    public Product(String productName, String productDescription, long categoryId, String imageUrl, Double price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product() {}
}
