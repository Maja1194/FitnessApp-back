package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.ProgramEntity;
import com.example.onlinefitnessback.models.entities.UserEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {
    private Integer id;
    private String text;
    private Timestamp date;
    private String video;
    private UserEntity user;
    private ProgramEntity program;

}
