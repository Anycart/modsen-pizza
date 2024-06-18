package by.modsen.pizza.service.impl;

import by.modsen.pizza.dto.ProductDto;
import by.modsen.pizza.service.ProductService;
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
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long id) {
        return null;
    }
}
