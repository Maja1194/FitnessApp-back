package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PictureRequest {
    @NotBlank
    private String url;
    @NotNull
    private Integer programId;

}
