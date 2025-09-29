package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.JwtUser;
import com.example.onlinefitnessback.models.dto.User;
import com.example.onlinefitnessback.models.entities.UserEntity;
import com.example.onlinefitnessback.models.requests.PasswordUpdateRequest;
import com.example.onlinefitnessback.models.requests.UserUpdateRequest;
import com.example.onlinefitnessback.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update/{username}")
    public User update(@PathVariable String username, @RequestBody @Valid UserUpdateRequest request,
                       Authentication auth){
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if(!jwtUser.getUsername().equals(username)){
            //dodati izuzetak
        }
        return userService.updateUser(username, request, auth);
    }

    @PutMapping("/update-pass/{username}")
    public User update(@PathVariable String username, @RequestBody @Valid PasswordUpdateRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (!jwtUser.getUsername().equals(username)) {
            //dodati izuzetak
        }

            return userService.updatePassword(username, request, auth);
    }

    @GetMapping
    public List<User> getAll(Authentication authentication){
        return userService.getAll(authentication);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<UserEntity> getUserByUsername(@RequestParam String username) {
        UserEntity user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

}
