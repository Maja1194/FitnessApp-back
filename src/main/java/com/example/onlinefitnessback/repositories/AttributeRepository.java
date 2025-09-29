package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.AttributeEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {
    List<AttributeEntity> getAllByCategory_Id(Integer categoryId);

}
