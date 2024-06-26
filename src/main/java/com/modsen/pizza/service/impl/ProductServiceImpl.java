package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.ProductDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.entity.Product;
import com.modsen.pizza.repository.CategoryRepository;
import com.modsen.pizza.repository.ProductRepository;
import com.modsen.pizza.service.ProductService;
import com.modsen.pizza.util.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productRepository.save(productConverter.convertToProduct(productDto));
        return productConverter.convertToProductDTO(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Product updateProduct = productRepository.save(product);
        return productConverter.convertToProductDTO(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
         productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productConverter::convertToProductDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll()
                .stream()
                .map(productConverter::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);

        return category.getProducts().stream()
                .map(productConverter::convertToProductDTO)
                .collect(Collectors.toList());
    }
}
