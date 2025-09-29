package com.example.onlinefitnessback.services;


import com.example.onlinefitnessback.models.dto.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Integer getCategoryIdByName(String categoryName);

}
