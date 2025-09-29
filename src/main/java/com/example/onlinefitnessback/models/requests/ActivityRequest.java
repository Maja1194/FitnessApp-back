package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;


public class ActivityRequest {

    @NotBlank
    private String exercise;
    @NotNull
    private Integer duration;
    @NotBlank
    private String result;
    @NotBlank
    private String intensity;
    @NotNull
    private Integer weight;
    @NotBlank
    private Date date;
    @NotNull
    private Integer userId;
}
