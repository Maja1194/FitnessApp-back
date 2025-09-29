package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.models.dto.LoginResponse;
import com.example.onlinefitnessback.models.dto.User;
import com.example.onlinefitnessback.models.requests.LoginRequest;

public interface AuthService {
    public void sendActivationEmail(String email, String activationLink);
    LoginResponse login(LoginRequest loginRequest);

    User activateAccount(String token);
}
