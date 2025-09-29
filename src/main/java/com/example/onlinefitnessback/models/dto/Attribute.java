package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class Attribute {
    private Integer id;
    private String attributeName;
    @JsonIgnore
    private CategoryEntity category;

}
