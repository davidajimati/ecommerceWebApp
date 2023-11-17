package com.personaproject.ecommercewebapp.dtos;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductDTO {
    private @NotEmpty String productName;
    private @NotEmpty String productDescription;
    private @NotEmpty Double categoryId;
    private @NotEmpty String imageUrl;
}
