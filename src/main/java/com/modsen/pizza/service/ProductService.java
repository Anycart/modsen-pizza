package com.modsen.pizza.service;

import com.modsen.pizza.dto.ProductDto;
import com.modsen.pizza.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto);
    void updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts(Pageable pageable);
    List<Product> getProductsByCategory(Long categoryId);
}