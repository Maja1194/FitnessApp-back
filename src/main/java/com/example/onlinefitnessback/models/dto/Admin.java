package com.example.onlinefitnessback.models.dto;

import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String role;

}
