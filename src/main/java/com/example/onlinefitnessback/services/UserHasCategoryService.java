package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.models.entities.UserHasCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHasCategoryService {
    List<Integer> getSelectedCategoryIdsForUser(Integer userId);
}
