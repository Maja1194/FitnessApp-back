package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.models.dto.Attribute;
import com.example.onlinefitnessback.models.entities.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeService {
    List<Attribute> getAllByCategoryId(Integer id);
}
