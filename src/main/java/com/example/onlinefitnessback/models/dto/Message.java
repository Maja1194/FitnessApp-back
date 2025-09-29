package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Integer id;
    private String text;
    private Boolean msgStatus;
    private User user;
    private Integer recipientId;
}
