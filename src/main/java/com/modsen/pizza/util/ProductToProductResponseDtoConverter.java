package com.modsen.pizza.util;

import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import com.modsen.pizza.dto.repsonse.ProductResponseDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductResponseDtoConverter implements Converter<Product, ProductResponseDto> {
    @Override
    public ProductResponseDto convert(Product source) {
        Category category = source.getCategory();
        CategoryResponseDto categoryResponseDto = null;

        if (category != null) {
            categoryResponseDto = CategoryResponseDto.builder()
                    .id(category.getId())
                    .description(category.getDescription())
                    .name(category.getName())
                    .build();
        }

        return ProductResponseDto.builder()
                .id(source.getId())
                .price(source.getPrice())
                .name(source.getName())
                .description(source.getDescription())
                .category(categoryResponseDto)
                .image(source.getImage())
                .build();
    }
}
