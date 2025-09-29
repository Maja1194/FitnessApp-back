package com.example.onlinefitnessback.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_has_program", schema = "fitnesprogram", catalog = "")
public class UserHasProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "isFinished")
    private Boolean isFinished;
    @Basic
    @Column(name = "statrDate")
    private Date startDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id_program", referencedColumnName = "id_program", nullable = false)
    private ProgramEntity program;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}
