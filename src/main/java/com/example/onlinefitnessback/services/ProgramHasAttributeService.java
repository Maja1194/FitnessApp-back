package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.models.dto.ProgramHasAttribute;
import com.example.onlinefitnessback.models.entities.ProgramHasAttributeEntity;
import com.example.onlinefitnessback.models.requests.ProgramHasAttributeRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProgramHasAttributeService {
    void addAttribute(ProgramHasAttributeRequest programHasAttributeRequest, Authentication authentication);
    List<ProgramHasAttribute> getAttributesByProgramId(Integer programId);
}
