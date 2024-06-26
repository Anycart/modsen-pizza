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
public class ProductConverter {
    private final ModelMapper modelMapper;
    public Product convertToProduct(ProductDto productDto){
        return modelMapper.map(productDto, Product.class);
    }
    public ProductDto convertToProductDTO(Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}

