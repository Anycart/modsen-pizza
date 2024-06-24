package com.modsen.pizza.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
public class CategoryDto {
    @Min(value = 0)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    private List<ProductDto> products;
}
