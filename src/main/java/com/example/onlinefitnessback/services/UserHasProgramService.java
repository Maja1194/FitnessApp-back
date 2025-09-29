package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.models.dto.Program;
import com.example.onlinefitnessback.models.entities.UserHasProgramEntity;
import com.example.onlinefitnessback.models.requests.UserHasProgramRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserHasProgramService {
    boolean userHasProgram(Integer userId, Integer programId, Authentication authentication);
    List<Program> getPurchasedPrograms(Integer id, Authentication auth);

    void addUserHasProgram(UserHasProgramRequest userHasProgramRequest, Authentication auth);
}
