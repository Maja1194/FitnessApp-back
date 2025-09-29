package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String city;
    @NotBlank
    private String email;
    private String avatar;
    @NotBlank
    private String status;
}
