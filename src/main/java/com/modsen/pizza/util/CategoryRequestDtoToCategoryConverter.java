package com.modsen.pizza.util;

import com.modsen.pizza.dto.request.CategoryRequestDto;
import com.modsen.pizza.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestDtoToCategoryConverter implements Converter<CategoryRequestDto, Category> {

    @Override
    public Category convert(CategoryRequestDto source) {
        return Category.builder()
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }
}
