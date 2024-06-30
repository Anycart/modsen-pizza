package com.modsen.pizza.util;

import com.modsen.pizza.dto.request.ProductRequestDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.entity.Product;
import com.modsen.pizza.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ProductRequestDtoToProductConverter implements Converter<ProductRequestDto, Product> {

    private final CategoryRepository categoryRepository;

    @Override
    public Product convert(ProductRequestDto source) {
        Category category = categoryRepository.findById(source.getCategoryId())
                .orElseThrow(NoSuchElementException::new);

        return Product.builder()
                .price(source.getPrice())
                .name(source.getName())
                .description(source.getDescription())
                .category(category)
                .image(source.getImage())
                .build();
    }
}
