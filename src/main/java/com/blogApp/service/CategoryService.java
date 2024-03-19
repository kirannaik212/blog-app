package com.blogApp.service;

import com.blogApp.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);

    void deleteCategory(Integer catId);

    CategoryDto getCategory(Integer catId);

    List<CategoryDto> getAllCategories();
}
