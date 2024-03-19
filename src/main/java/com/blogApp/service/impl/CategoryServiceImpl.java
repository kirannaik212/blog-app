package com.blogApp.service.impl;

import com.blogApp.dto.CategoryDto;
import com.blogApp.entity.Category;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.repositories.CategoryRepository;
import com.blogApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryDto1 = modelMapper.map(savedCategory, CategoryDto.class);
        return categoryDto1;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("user", "id", catId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
      //  category.setCategoryDescription();
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category", "id", +catId));
        categoryRepository.delete(category);
        // return null;
    }

    @Override
    public CategoryDto getCategory(Integer catId) {
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category", "id", catId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }
}
