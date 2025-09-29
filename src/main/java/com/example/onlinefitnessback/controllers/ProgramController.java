package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.JwtUser;
import com.example.onlinefitnessback.models.dto.Program;
import com.example.onlinefitnessback.models.entities.ProgramEntity;
import com.example.onlinefitnessback.models.requests.ProgramRequest;
import com.example.onlinefitnessback.repositories.UserRepository;
import com.example.onlinefitnessback.services.ProgramService;
import com.example.onlinefitnessback.services.UserHasProgramService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;
    private final UserHasProgramService userHasProgramService;
    private final UserRepository userRepository;

    public ProgramController(ProgramService programService, UserHasProgramService userHasProgramService, UserRepository userRepository) {
        this.programService = programService;
        this.userHasProgramService = userHasProgramService;
        this.userRepository = userRepository;
    }

    @GetMapping("/all-programs")
    public ResponseEntity<List<Program>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }

    @GetMapping("/my-programs")
    public ResponseEntity<List<Program>> getMyPrograms(Authentication auth) {
        Integer userId = getLoggedInUserId(auth);
        List<Program> myPrograms = programService.getMyPrograms(userId);
        return ResponseEntity.ok(myPrograms);
    }

    @GetMapping("/purchased-programs")
    public ResponseEntity<List<Program>> getPurchasedPrograms(Authentication auth) {
        Integer userId = getLoggedInUserId(auth);
        List<Program> purchasedPrograms = userHasProgramService.getPurchasedPrograms(userId, auth);
        return ResponseEntity.ok(purchasedPrograms);
    }

    private Integer getLoggedInUserId(Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        String username = jwtUser.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    @PostMapping
    public ResponseEntity<Integer> addNewProgram(@RequestBody @Valid ProgramRequest programRequest, Authentication auth) {
        System.out.println("Received request to create program: " + programRequest);
        System.out.println("uslooo");
            ProgramEntity programId = programService.addNewProgram(programRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(programId.getIdProgram());

    }

}
