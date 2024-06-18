package by.modsen.pizza.dto;

import lombok.*;

@Data
public class ProductDto {
    private String name;
    private String description;
    private String category;
    private double price;
}
