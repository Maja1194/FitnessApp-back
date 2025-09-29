package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.requests.UserHasProgramRequest;
import com.example.onlinefitnessback.services.UserHasProgramService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-has-program")
public class UserHasProgramController {

    private final UserHasProgramService userHasProgramService;


    public UserHasProgramController(UserHasProgramService userHasProgramService) {
        this.userHasProgramService = userHasProgramService;
    }

    @PostMapping
    public ResponseEntity<?> addUserHasProgram(@RequestBody @Valid UserHasProgramRequest userHasProgramRequest,
                                               Authentication authentication) {
        try {
            userHasProgramService.addUserHasProgram(userHasProgramRequest, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
