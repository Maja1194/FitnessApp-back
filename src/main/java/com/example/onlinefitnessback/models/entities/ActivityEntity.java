package com.example.onlinefitnessback.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "activity", schema = "fitnesprogram", catalog = "")
public class ActivityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "exercise")
    private String exercise;
    @Basic
    @Column(name = "duration")
    private Integer duration;
    @Basic
    @Column(name = "result")
    private String result;
    @Basic
    @Column(name = "intensity")
    private String intensity;
    @Basic
    @Column(name = "weight")
    private Integer weight;
    @Basic
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "User_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}
