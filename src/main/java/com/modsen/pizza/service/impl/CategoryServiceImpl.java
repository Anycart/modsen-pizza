package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.CategoryDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.repository.CategoryRepository;
import com.modsen.pizza.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        categoryRepository.save(modelMapper.map(categoryDto, Category.class));
    }

    @Override
    public void updateCategory(Long id, CategoryDto categoryDto) {
        Category updatedCategory = modelMapper.map(categoryDto, Category.class);
        updatedCategory.setId(id);
        categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Category> getAllCategories(Pageable pageable) {
        return null;
    }
}
