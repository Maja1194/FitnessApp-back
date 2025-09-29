package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.ProgramEntity;
import com.example.onlinefitnessback.models.entities.UserEntity;
import lombok.Data;

import java.sql.Date;

@Data
public class UserHasProgram {
    private Integer id;
    private Boolean isFinished;
    private Date statrDate;
    private Program program;
    private User user;

}
