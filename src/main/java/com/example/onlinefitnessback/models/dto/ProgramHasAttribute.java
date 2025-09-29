package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.AttributeEntity;
import com.example.onlinefitnessback.models.entities.ProgramEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProgramHasAttribute {
    private Integer id;
    private String value;
    private Attribute attribute;
    private Program program;

}
