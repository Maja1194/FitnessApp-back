package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.ProgramHasAttribute;
import com.example.onlinefitnessback.models.entities.ProgramHasAttributeEntity;
import com.example.onlinefitnessback.models.requests.ProgramHasAttributeRequest;
import com.example.onlinefitnessback.services.ProgramHasAttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program-has-attributes")
public class ProgramHasAttributeController {

    private final ProgramHasAttributeService programHasAttributeService;

    public ProgramHasAttributeController(ProgramHasAttributeService programHasAttributeService) {
        this.programHasAttributeService = programHasAttributeService;
    }

    @PostMapping
    public ResponseEntity<Void> addAttribute(@RequestBody ProgramHasAttributeRequest programHasAttributeRequest, Authentication authentication) {
        try {
            programHasAttributeService.addAttribute(programHasAttributeRequest, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{programId}")
    public ResponseEntity<List<ProgramHasAttribute>> getAttributesByProgramId(@PathVariable Integer programId) {
        List<ProgramHasAttribute> attributes = programHasAttributeService.getAttributesByProgramId(programId);
        return ResponseEntity.ok(attributes);
    }
}
