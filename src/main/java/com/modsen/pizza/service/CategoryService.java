package com.modsen.pizza.service;

import com.modsen.pizza.dto.CategoryDto;
import com.modsen.pizza.entity.Category;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);
    void updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategories(Pageable pageable);
}
