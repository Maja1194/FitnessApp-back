package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.exceptions.NotFoundException;
import com.example.onlinefitnessback.models.dto.User;
import com.example.onlinefitnessback.models.entities.UserEntity;
import com.example.onlinefitnessback.models.requests.PasswordUpdateRequest;
import com.example.onlinefitnessback.models.requests.SignUpRequest;
import com.example.onlinefitnessback.models.requests.UserUpdateRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void signUp(SignUpRequest request);
    User updateUser(String username, UserUpdateRequest request, Authentication auth) ;
    User updatePassword(String username, PasswordUpdateRequest request, Authentication auth) ;
    List<User> getAll(Authentication auth);

    UserEntity findByUsername(String username);
}
