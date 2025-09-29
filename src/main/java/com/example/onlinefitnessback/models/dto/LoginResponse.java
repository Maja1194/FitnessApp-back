package com.example.onlinefitnessback.models.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer idUser;

    private String name;

    private String surname;

    private String username;

    private String city;

    private String email;

    private String avatar;

    private String status;

    private String token;
}
