package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.CategoryEntity;
import com.example.onlinefitnessback.models.entities.UserEntity;
import lombok.Data;

@Data
public class UserHasCategory {
    private Integer id;
    private UserEntity user;
    private CategoryEntity category;

}
