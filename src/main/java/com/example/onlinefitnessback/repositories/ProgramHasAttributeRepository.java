package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.ProgramHasAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramHasAttributeRepository extends JpaRepository<ProgramHasAttributeEntity, Integer> {
    List<ProgramHasAttributeEntity> findByProgram_IdProgram(Integer id);


}
