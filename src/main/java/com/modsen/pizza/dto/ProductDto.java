package com.modsen.pizza.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductDto {
    @Min(value = 0)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @Min(value = 0)
    private Double price;
    @Valid
    @JsonBackReference
    private CategoryDto category;
}
