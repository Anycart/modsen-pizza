package by.modsen.pizza.service;


import by.modsen.pizza.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public ProductDto createProduct(ProductDto productDto){
        return new ProductDto();
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        return productDto;
    }

    public void deleteProduct(Long id) {

    }

    public ProductDto getProductById(Long id) {
        return new ProductDto();
    }

    public List<ProductDto> getAllProducts(){
        return new ArrayList<>();
    }

    public List<ProductDto> getProductsByCategory(Long id) {
        return new ArrayList<>();
    }
}
