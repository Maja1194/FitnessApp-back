package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.exceptions.NotFoundException;
import com.example.onlinefitnessback.models.dto.Program;
import com.example.onlinefitnessback.models.entities.ProgramEntity;
import com.example.onlinefitnessback.models.requests.ProgramRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProgramService {

    List<Program> getAllPrograms();
    List<Program> getMyPrograms(Integer userId);
    ProgramEntity addNewProgram(ProgramRequest programRequest);
}
