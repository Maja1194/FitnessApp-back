package com.example.onlinefitnessback.services.impl;


import com.example.onlinefitnessback.models.dto.Category;
import com.example.onlinefitnessback.models.entities.CategoryEntity;
import com.example.onlinefitnessback.repositories.CategoryRepository;
import com.example.onlinefitnessback.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryEntityRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryEntityRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, Category.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getCategoryIdByName(String categoryName) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryName);
        if (categoryEntity != null) {
            return categoryEntity.getId();
        }
        return null;
    }


}
