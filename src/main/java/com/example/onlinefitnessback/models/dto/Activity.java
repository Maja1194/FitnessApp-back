package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.UserEntity;
import lombok.Data;

import java.sql.Date;

@Data

public class Activity {

    private Integer id;
    private String exercise;
    private Integer duration;
    private String result;
    private String intensity;
    private Integer weight;
    private Date date;
    private UserEntity user;
}
