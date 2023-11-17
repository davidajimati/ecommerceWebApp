package com.personaproject.ecommercewebapp.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDTO {

    private @NotEmpty String categoryName;

    private @NotEmpty String description;

    private @NotNull String imageUrl;
}
