package com.modsen.pizza;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modsen.pizza.controller.CategoryController;
import com.modsen.pizza.dto.request.CategoryRequestDto;
import com.modsen.pizza.dto.repsonse.CategoryResponseDto;
import com.modsen.pizza.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private CategoryRequestDto categoryRequestDto;
    private CategoryResponseDto categoryResponseDto;

    @BeforeEach
    void setUp() {
        categoryRequestDto = new CategoryRequestDto("Pizza", "Delicious pizza");
        categoryResponseDto = new CategoryResponseDto(1L, "Pizza", "Delicious pizza");
    }

    @Test
    void testCreateCategory() throws Exception {
        Mockito.when(categoryService.createCategory(any(CategoryRequestDto.class))).thenReturn(categoryResponseDto);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(categoryResponseDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(categoryResponseDto.getName())))
                .andExpect(jsonPath("$.description", is(categoryResponseDto.getDescription())));
    }

    @Test
    void testUpdateCategory() throws Exception {
        Mockito.when(categoryService.updateCategory(eq(1L), any(CategoryRequestDto.class))).thenReturn(categoryResponseDto);

        mockMvc.perform(put("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(categoryResponseDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(categoryResponseDto.getName())))
                .andExpect(jsonPath("$.description", is(categoryResponseDto.getDescription())));
    }

    @Test
    void testDeleteCategory() throws Exception {
        Mockito.doNothing().when(categoryService).deleteCategory(1L);

        mockMvc.perform(delete("/api/categories/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetCategoryById() throws Exception {
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(categoryResponseDto);

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(categoryResponseDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(categoryResponseDto.getName())))
                .andExpect(jsonPath("$.description", is(categoryResponseDto.getDescription())));
    }

    @Test
    void testGetAllCategories() throws Exception {
        Mockito.when(categoryService.getAllCategories(any())).thenReturn(Collections.singletonList(categoryResponseDto));

        mockMvc.perform(get("/api/categories")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(categoryResponseDto.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(categoryResponseDto.getName())))
                .andExpect(jsonPath("$[0].description", is(categoryResponseDto.getDescription())));
    }
}
