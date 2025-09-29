package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.*;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String city;
    private String email;
    private String avatar;
    private String status;


}
