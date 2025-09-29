package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<PictureEntity, Integer> {
}
