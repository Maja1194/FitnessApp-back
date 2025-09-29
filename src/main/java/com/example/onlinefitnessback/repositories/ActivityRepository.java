package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity,Integer> {
    List<ActivityEntity> findByUserId(Integer userId);
}
