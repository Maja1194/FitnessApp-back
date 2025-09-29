package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class CommentRequest {
    @NotBlank
    private String text;
    @NotBlank
    private Timestamp date;
    @NotBlank
    private String video;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer programId;

}
