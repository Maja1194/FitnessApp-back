package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.UserHasCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHasCategoryRepository extends JpaRepository<UserHasCategoryEntity, Integer> {
    List<UserHasCategoryEntity> findByUserId(Integer userId);

}
