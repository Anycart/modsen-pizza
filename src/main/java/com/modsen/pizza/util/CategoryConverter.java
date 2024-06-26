package com.modsen.pizza.util;

import com.modsen.pizza.dto.CategoryDto;
import com.modsen.pizza.dto.ProductDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ModelMapper modelMapper;

    public Category convertToCategory(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        if (category.getProducts() != null) {
            category.getProducts().forEach(product -> product.setCategory(category));
        }
        return category;
    }

    public CategoryDto convertToCategoryDTO(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }
}
