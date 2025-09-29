package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
