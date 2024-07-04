package com.modsen.pizza.repository;

import com.modsen.pizza.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setUp() {
        category1 = Category.builder()
                .name("Test Category 1")
                .description("Description 1")
                .build();

        category2 = Category.builder()
                .name("Test Category 2")
                .description("Description 2")
                .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

    @Test
    public void testSaveCategory() {
        Category category = Category.builder()
                .name("New Test Category")
                .description("New Description")
                .build();

        categoryRepository.save(category);

        assertThat(category.getId()).isNotNull();

        Category savedCategory = categoryRepository.findById(category.getId()).orElse(null);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("New Test Category");
        assertEquals(3, categoryRepository.count());
    }

    @Test
    public void testUpdateCategory() {
        String updatedDescription = "Updated description";

        category1.setDescription(updatedDescription);
        categoryRepository.save(category1);

        Category updatedCategory = categoryRepository.findById(category1.getId()).orElse(null);
        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getDescription()).isEqualTo(updatedDescription);
    }

    @Test
    public void testDeleteCategory() {
        categoryRepository.delete(category1);

        Optional<Category> deletedCategoryOptional = categoryRepository.findById(category1.getId());
        assertThat(deletedCategoryOptional).isEmpty();
    }

    @Test
    public void testFindAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        assertThat(categories).isNotEmpty();
        assertEquals(2, categories.size());
        assertThat(categories).contains(category1, category2);
    }

    @Test
    public void testFindById() {
        Optional<Category> foundCategoryOptional = categoryRepository.findById(category1.getId());
        assertThat(foundCategoryOptional).isPresent();
        assertEquals("Test Category 1", foundCategoryOptional.get().getName());
    }
}
