package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class UserHasProgramRequest {
    @NotNull
    private Boolean isFinished;
    @NotNull
    private Date startDate;
    @NotNull
    private Integer programId;
    @NotNull
    private Integer userId;

}
