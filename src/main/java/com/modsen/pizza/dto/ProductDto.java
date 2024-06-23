package com.modsen.pizza.dto;

import com.modsen.pizza.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class ProductDto {
    @Min(value = 0)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @Min(value = 0)
    private Double price;
}
