package com.modsen.pizza.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 25, message = "Name must be between 1 and 25 characters")
    private String name;
}