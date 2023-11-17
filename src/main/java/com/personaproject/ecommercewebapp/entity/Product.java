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
    private Double categoryId;

    @Column(name = "image_url")
    private String imageUrl;
}
