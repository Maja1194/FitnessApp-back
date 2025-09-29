package com.example.onlinefitnessback.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "user", schema = "fitnesprogram", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "status")
    private String status;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ActivityEntity> activities;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<MessageEntity> messages;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ProgramEntity> programs;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserHasCategoryEntity> userHasCategories;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserHasProgramEntity> userHasPrograms;

}
