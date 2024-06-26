package com.modsen.pizza.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
public class CategoryDto {
    @Min(value = 0)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @Valid
    @JsonManagedReference
    private List<ProductDto> products;
}
