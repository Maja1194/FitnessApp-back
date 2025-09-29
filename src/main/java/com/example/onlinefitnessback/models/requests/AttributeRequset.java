package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class AttributeRequset {
    @NotBlank
    private String attributeName;
    @NotNull
    private Integer categoryId;

}
