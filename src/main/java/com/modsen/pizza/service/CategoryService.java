package com.modsen.pizza.service;

import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import com.modsen.pizza.dto.request.CategoryRequestDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);
    void deleteCategory(Long id);
    CategoryResponseDto getCategoryById(Long id);
    List<CategoryResponseDto> getAllCategories(Pageable pageable);
}
