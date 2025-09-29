package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {
    @NotBlank
    private String categoryName;

}
