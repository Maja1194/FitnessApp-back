package com.example.onlinefitnessback.models.requests;

import com.example.onlinefitnessback.models.dto.Picture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProgramRequest {
    @NotBlank
    private String programName;
    @NotBlank
    private String description;
    @NotNull
    private Integer programPrice;
    @NotBlank
    private String level;
    @NotNull
    private Integer duration;
    @NotBlank
    private String location;
    @NotBlank
    private String instructorInformation;
    @NotBlank
    private String phone;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer id_category;
    private String video_url;
    private List<Picture> pictures;

}
