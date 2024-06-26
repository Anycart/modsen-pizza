package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.CategoryDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.repository.CategoryRepository;
import com.modsen.pizza.service.CategoryService;
import com.modsen.pizza.util.CategoryConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.log4j2.ColorConverter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryConverter.convertToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.convertToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return categoryConverter.convertToCategoryDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryConverter::convertToCategoryDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryConverter::convertToCategoryDTO)
                .toList();
    }
}
