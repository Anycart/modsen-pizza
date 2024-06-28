package com.modsen.pizza.service.impl;

import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import com.modsen.pizza.dto.request.CategoryRequestDto;
import com.modsen.pizza.entity.Category;
import com.modsen.pizza.repository.CategoryRepository;
import com.modsen.pizza.service.CategoryService;
import com.modsen.pizza.util.CategoryRequestDtoToCategoryConverter;
import com.modsen.pizza.util.CategoryToCategoryResponseDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryResponseDtoConverter categoryToCategoryResponseDtoConverter;
    private final CategoryRequestDtoToCategoryConverter categoryRequestDtoToCategoryConverter;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDtoToCategoryConverter.convert(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryToCategoryResponseDtoConverter.convert(savedCategory);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return categoryToCategoryResponseDtoConverter.convert(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryToCategoryResponseDtoConverter::convert)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryToCategoryResponseDtoConverter::convert)
                .toList();
    }
}
