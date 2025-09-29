package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.AttributeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AttributeHasValue {
    private Integer id;
    private String value;
    @JsonIgnore
    private Attribute attribute;

}
