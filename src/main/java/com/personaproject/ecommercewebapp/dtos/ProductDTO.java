package com.personaproject.ecommercewebapp.dtos;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {
    private @NotEmpty String productName;
    private @NotEmpty String productDescription;
    private @NotEmpty Long categoryId;
    private @NotEmpty String imageUrl;
    private @NotEmpty Double price;
}

