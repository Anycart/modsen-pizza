package com.modsen.pizza.dto.repsonse;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private CategoryResponseDto category;
}
