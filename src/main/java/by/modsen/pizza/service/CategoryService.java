package by.modsen.pizza.service;


import by.modsen.pizza.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {


    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryDto;
    }


    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return categoryDto;
    }

    public void deleteCategory(Long id) {

    }

    public CategoryDto getCategoryById(Long id) {

        return new CategoryDto();
    }

    public List<CategoryDto> getAllCategories() {
        return new ArrayList<>();
    }
}
