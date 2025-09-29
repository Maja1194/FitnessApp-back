package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.LoginResponse;
import com.example.onlinefitnessback.models.dto.User;
import com.example.onlinefitnessback.models.requests.LoginRequest;
import com.example.onlinefitnessback.models.requests.SignUpRequest;
import com.example.onlinefitnessback.services.AuthService;
import com.example.onlinefitnessback.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest request) {
        try {
            userService.signUp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful! Please check your email to confirm.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request){
        return authService.login(request);
    }

    @GetMapping("/activate")
    public ResponseEntity<User> activateAccount(@RequestParam String token) {
        User user = authService.activateAccount(token);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
