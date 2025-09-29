package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.AttributehasvalueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeHasValueRepository extends JpaRepository<AttributehasvalueEntity, Integer> {
    List<AttributehasvalueEntity> getAllByAttribute_Id(Integer attributeId);

}
