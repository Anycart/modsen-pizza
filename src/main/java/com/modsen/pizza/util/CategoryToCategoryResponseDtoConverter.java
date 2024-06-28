package com.modsen.pizza.util;

import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import com.modsen.pizza.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryResponseDtoConverter implements Converter<Category, CategoryResponseDto> {

    @Override
    public CategoryResponseDto convert(Category source) {
       return CategoryResponseDto.builder()
               .id(source.getId())
               .name(source.getName())
               .description(source.getDescription())
               .build();
    }
}
