package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;

public class CounselorRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String password;
    @NotBlank
    private String username;

}
