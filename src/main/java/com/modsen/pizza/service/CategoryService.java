package com.modsen.pizza.service;

import com.modsen.pizza.dto.CategoryDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories(Pageable pageable);
}
