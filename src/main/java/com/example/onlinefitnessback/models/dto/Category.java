package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.AttributeEntity;
import com.example.onlinefitnessback.models.entities.UserHasCategoryEntity;
import lombok.Data;

@Data
public class Category {
    private Integer id;
    private String categoryName;

}
