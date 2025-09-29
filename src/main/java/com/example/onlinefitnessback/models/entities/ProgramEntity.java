package com.example.onlinefitnessback.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "program", schema = "fitnesprogram", catalog = "")
public class ProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_program")
    private Integer idProgram;

    @Basic
    @Column(name = "program_name")
    private String programName;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "program_price")
    private Integer programPrice;

    @Basic
    @Column(name = "level")
    private String level;

    @Basic
    @Column(name = "duration")
    private Integer duration;

    @Basic
    @Column(name = "location")
    private String location;

    @Basic
    @Column(name = "instructor_information")
    private String instructorInformation;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "video_url", length = 300, nullable = true)
    private String videoUrl;


    @JsonIgnore
    @OneToMany(mappedBy = "program")
    private List<CommentEntity> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "program")
    private List<PictureEntity> pictures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "User_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "program")
    private List<ProgramHasAttributeEntity> programHasAttributes;

    @JsonIgnore
    @OneToMany(mappedBy = "program")
    private List<UserHasProgramEntity> userHasProgramsByIdProgram;
}
