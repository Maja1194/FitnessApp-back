package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.ProgramEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Picture {
    private String url;
    private Integer programId;

}
