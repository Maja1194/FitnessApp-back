package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AttributeHasValueRequest {
    @NotBlank
    private String value;
    @NotNull
    private Integer attributeId;

}
