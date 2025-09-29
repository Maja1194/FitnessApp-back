package com.example.onlinefitnessback.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {
    @NotBlank
    private String text;
    @NotNull
    private Integer userId;

}
