package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;


public class AdminRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String role;

}
