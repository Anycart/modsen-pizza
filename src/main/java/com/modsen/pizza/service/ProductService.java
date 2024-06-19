package com.modsen.pizza.service;

import com.modsen.pizza.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts(Pageable pageable);
    List<ProductDto> getProductsByCategory(Long id);
}
