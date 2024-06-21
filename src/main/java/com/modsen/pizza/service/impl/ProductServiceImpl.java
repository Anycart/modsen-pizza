package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.ProductDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.entity.Order;
import com.modsen.pizza.entity.Product;
import com.modsen.pizza.repository.OrderRepository;
import com.modsen.pizza.repository.ProductRepository;
import com.modsen.pizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createProduct(ProductDto productDto) {
        productRepository.save(modelMapper.map(productDto, Product.class));
    }

    @Override
    public void updateProduct(Long id, ProductDto productDto) {
        Product updatedProduct = modelMapper.map(productDto, Product.class);
        updatedProduct.setId(id);
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(Long id) {
        return productRepository.findByCategory_Id(id);
    }
}
