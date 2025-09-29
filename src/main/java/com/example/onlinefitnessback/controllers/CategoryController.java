package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.Category;
import com.example.onlinefitnessback.services.CategoryService;
import com.example.onlinefitnessback.services.UserHasCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserHasCategoryService userHasCategoryService;

    public CategoryController(CategoryService categoryService, UserHasCategoryService userHasCategoryService){
        this.categoryService=categoryService;
        this.userHasCategoryService = userHasCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/selected/{userId}")
    public ResponseEntity<List<Integer>> getSelectedCategories(@PathVariable Integer userId) {
        List<Integer> selectedCategoryIds = userHasCategoryService.getSelectedCategoryIdsForUser(userId);
        return ResponseEntity.ok(selectedCategoryIds);
    }

    @GetMapping("/get-id-by-name")
    public ResponseEntity<Integer> getCategoryIdByName(@RequestParam String categoryName) {
        Integer categoryId = categoryService.getCategoryIdByName(categoryName);
        if (categoryId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryId);
    }

}
