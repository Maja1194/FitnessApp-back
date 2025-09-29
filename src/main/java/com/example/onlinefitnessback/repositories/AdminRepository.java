package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
}
