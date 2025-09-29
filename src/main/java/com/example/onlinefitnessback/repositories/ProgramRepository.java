package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository <ProgramEntity, Integer> {
    List<ProgramEntity> findAllByUserId(Integer userId);
}
