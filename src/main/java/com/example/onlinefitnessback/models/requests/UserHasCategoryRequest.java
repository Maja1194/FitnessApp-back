package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotNull;

public class UserHasCategoryRequest {

    @NotNull
    private Integer userId;
    @NotNull
    private Integer categoryId;

}
