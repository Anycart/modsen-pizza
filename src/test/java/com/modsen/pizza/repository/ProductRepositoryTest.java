package com.modsen.pizza.repository;

import com.modsen.pizza.entity.Category;
import com.modsen.pizza.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveProduct() {
        Category category = Category.builder()
                .name("Pizza")
                .description("All kinds of pizzas")
                .build();

        categoryRepository.save(category);

        Product product = Product.builder()
                .name("Margherita")
                .description("Classic cheese and tomato pizza")
                .price(8.99)
                .category(category)
                .image("margherita.jpg")
                .build();

        productRepository.save(product);

        assertThat(product.getId()).isNotNull();

        Optional<Product> savedProductOptional = productRepository.findById(product.getId());
        assertThat(savedProductOptional).isPresent();

        Product savedProduct = savedProductOptional.get();
        assertThat(savedProduct.getName()).isEqualTo("Margherita");
        assertThat(savedProduct.getDescription()).isEqualTo("Classic cheese and tomato pizza");
        assertThat(savedProduct.getPrice()).isEqualTo(8.99);
        assertThat(savedProduct.getCategory()).isEqualTo(category);
        assertThat(savedProduct.getImage()).isEqualTo("margherita.jpg");
    }

    @Test
    public void testFindById() {
        Category category = Category.builder()
                .name("Pizza")
                .description("All kinds of pizzas")
                .build();

        categoryRepository.save(category);

        Product product = Product.builder()
                .name("Margherita")
                .description("Classic cheese and tomato pizza")
                .price(8.99)
                .category(category)
                .image("margherita.jpg")
                .build();

        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Margherita");
    }

    @Test
    public void testFindAll() {
        Category category = Category.builder()
                .name("Pizza")
                .description("All kinds of pizzas")
                .build();

        categoryRepository.save(category);

        Product product1 = Product.builder()
                .name("Margherita")
                .description("Classic cheese and tomato pizza")
                .price(8.99)
                .category(category)
                .image("margherita.jpg")
                .build();

        Product product2 = Product.builder()
                .name("Pepperoni")
                .description("Pepperoni pizza with extra cheese")
                .price(10.99)
                .category(category)
                .image("pepperoni.jpg")
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts).isNotEmpty();
        assertThat(allProducts).contains(product1, product2);
    }

    @Test
    public void testUpdateProduct() {
        Category category = Category.builder()
                .name("Pizza")
                .description("All kinds of pizzas")
                .build();

        categoryRepository.save(category);

        Product product = Product.builder()
                .name("Margherita")
                .description("Classic cheese and tomato pizza")
                .price(8.99)
                .category(category)
                .image("margherita.jpg")
                .build();

        productRepository.save(product);

        product.setName("Margherita Updated");
        product.setPrice(9.99);
        productRepository.save(product);

        Optional<Product> updatedProduct = productRepository.findById(product.getId());
        assertThat(updatedProduct).isPresent();
        assertThat(updatedProduct.get().getName()).isEqualTo("Margherita Updated");
        assertThat(updatedProduct.get().getPrice()).isEqualTo(9.99);
    }

    @Test
    public void testDeleteProduct() {
        Category category = Category.builder()
                .name("Pizza")
                .description("All kinds of pizzas")
                .build();

        categoryRepository.save(category);

        Product product = Product.builder()
                .name("Margherita")
                .description("Classic cheese and tomato pizza")
                .price(8.99)
                .category(category)
                .image("margherita.jpg")
                .build();

        productRepository.save(product);

        productRepository.delete(product);

        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        assertThat(deletedProduct).isNotPresent();
    }
}

