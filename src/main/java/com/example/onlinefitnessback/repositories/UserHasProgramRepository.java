package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.UserHasProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHasProgramRepository extends JpaRepository<UserHasProgramEntity, Integer> {
    List<UserHasProgramEntity> getAllByUserId(Integer userId);
    boolean existsByUserIdAndProgramIdProgram(Integer userId, Integer programIdProgram);

    UserHasProgramEntity findByUserIdAndProgramIdProgram(Integer userId, Integer programIdProgram);

}
