package com.example.onlinefitnessback.models.dto;

import com.example.onlinefitnessback.models.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class Program {
    private Integer idProgram;
    private String programName;
    private String description;
    private Integer programPrice;
    private String level;
    private Integer duration;
    private String location;
    private String instructorInformation;
    private String phone;
    @JsonIgnore
    private List<Comment> comments;
    @JsonIgnore
    private List<Picture> pictures;
    private User user;
    private Category category;
    private String videoUrl;

}
