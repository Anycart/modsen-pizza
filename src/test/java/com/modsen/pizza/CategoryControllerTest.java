package com.modsen.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import com.modsen.pizza.controller.CategoryController;
import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import com.modsen.pizza.dto.request.CategoryRequestDto;
import com.modsen.pizza.service.CategoryService;
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
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Test
    public void testCreateCategory() {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("Electronics");
        requestDto.setDescription("Various electronic items");

        CategoryResponseDto mockCategory = new CategoryResponseDto();
        mockCategory.setId(1L);
        mockCategory.setName("Electronics");
        mockCategory.setDescription("Various electronic items");

        when(categoryService.createCategory(any(CategoryRequestDto.class))).thenReturn(mockCategory);

        ResponseEntity<CategoryResponseDto> response = categoryController.createCategory(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockCategory, response.getBody());
    }

    @Test
    public void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("Electronics Updated");
        requestDto.setDescription("Updated description");

        CategoryResponseDto mockCategory = new CategoryResponseDto();
        mockCategory.setId(categoryId);
        mockCategory.setName("Electronics Updated");
        mockCategory.setDescription("Updated description");

        when(categoryService.updateCategory(eq(categoryId), any(CategoryRequestDto.class))).thenReturn(mockCategory);

        ResponseEntity<CategoryResponseDto> response = categoryController.updateCategory(categoryId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategory, response.getBody());
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;
        doNothing().when(categoryService).deleteCategory(categoryId);

        ResponseEntity<Void> response = categoryController.deleteCategory(categoryId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService).deleteCategory(categoryId);
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryResponseDto mockCategory = new CategoryResponseDto();
        mockCategory.setId(categoryId);
        mockCategory.setName("Electronics");
        mockCategory.setDescription("Various electronic items");

        when(categoryService.getCategoryById(categoryId)).thenReturn(mockCategory);

        ResponseEntity<CategoryResponseDto> response = categoryController.getCategoryById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategory, response.getBody());
    }

    @Test
    public void testGetAllCategories() {
        List<CategoryResponseDto> mockCategories = List.of(
                new CategoryResponseDto(1L, "Electronics", "Various electronic items"),
                new CategoryResponseDto(2L, "Furniture", "Different kinds of furniture")
        );

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(categoryService.getAllCategories(pageRequest)).thenReturn(mockCategories);
        ResponseEntity<List<CategoryResponseDto>> response = categoryController.getAllCategories(0, 10, "id");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCategories, response.getBody());
    }
}