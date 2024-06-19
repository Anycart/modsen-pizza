package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.ProductDto;
import com.modsen.pizza.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
    }

    @Override
    public ProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long id) {
        return null;
    }
}
