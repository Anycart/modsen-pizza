package com.modsen.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import com.modsen.pizza.controller.ProductController;
import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import com.modsen.pizza.dto.repsonse.ProductResponseDto;
import com.modsen.pizza.dto.request.ProductRequestDto;
import com.modsen.pizza.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Pizza");
        requestDto.setDescription("Delicious cheese pizza");
        requestDto.setPrice(9.99);
        requestDto.setCategoryId(1L);

        ProductResponseDto mockProduct = new ProductResponseDto();
        mockProduct.setId(1L);
        mockProduct.setName("Pizza");
        mockProduct.setDescription("Delicious cheese pizza");
        mockProduct.setPrice(9.99);
        mockProduct.setCategory(new CategoryResponseDto(1L, "Food", "Various food items"));

        when(productService.createProduct(any(ProductRequestDto.class))).thenReturn(mockProduct);

        ResponseEntity<ProductResponseDto> response = productController.createProduct(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Pizza Updated");
        requestDto.setDescription("Delicious cheese pizza with extra cheese");
        requestDto.setPrice(11.99);
        requestDto.setCategoryId(1L);

        ProductResponseDto mockProduct = new ProductResponseDto();
        mockProduct.setId(productId);
        mockProduct.setName("Pizza Updated");
        mockProduct.setDescription("Delicious cheese pizza with extra cheese");
        mockProduct.setPrice(11.99);
        mockProduct.setCategory(new CategoryResponseDto(1L, "Food", "Various food items"));

        when(productService.updateProduct(eq(productId), any(ProductRequestDto.class))).thenReturn(mockProduct);

        ResponseEntity<ProductResponseDto> response = productController.updateProduct(productId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        doNothing().when(productService).deleteProduct(productId);

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService).deleteProduct(productId);
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;
        ProductResponseDto mockProduct = new ProductResponseDto();
        mockProduct.setId(productId);
        mockProduct.setName("Pizza");
        mockProduct.setDescription("Delicious cheese pizza");
        mockProduct.setPrice(9.99);
        mockProduct.setCategory(new CategoryResponseDto(1L, "Food", "Various food items"));

        when(productService.getProductById(productId)).thenReturn(mockProduct);

        ResponseEntity<ProductResponseDto> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
    }


    @Test
    public void testGetAllProducts() {
        List<ProductResponseDto> mockProducts = List.of(
                new ProductResponseDto(1L, "Pizza", "Delicious cheese pizza", 9.99, new CategoryResponseDto(1L, "Food", "Various food items")),
                new ProductResponseDto(2L, "Burger", "Juicy beef burger", 5.99, new CategoryResponseDto(1L, "Food", "Various food items"))
        );

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(productService.getAllProducts(pageRequest)).thenReturn(mockProducts);

        ResponseEntity<List<ProductResponseDto>> response = productController.getAllProducts(0, 10, "id");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProducts, response.getBody());
    }

    @Test
    public void testGetProductsByCategory() {
        Long categoryId = 1L;
        List<ProductResponseDto> mockProducts = List.of(
                new ProductResponseDto(1L, "Pizza", "Delicious cheese pizza", 9.99, new CategoryResponseDto(categoryId, "Food", "Various food items")),
                new ProductResponseDto(2L, "Burger", "Juicy beef burger", 5.99, new CategoryResponseDto(categoryId, "Food", "Various food items"))
        );

        when(productService.getProductsByCategory(categoryId)).thenReturn(mockProducts);

        ResponseEntity<List<ProductResponseDto>> response = productController.getProductsByCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProducts, response.getBody());
    }
}
