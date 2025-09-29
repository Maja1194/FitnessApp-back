package com.example.onlinefitnessback.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "message", schema = "fitnesprogram", catalog = "")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "msgStatus")
    private Boolean msgStatus;
    @ManyToOne
    @JoinColumn(name = "User_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;
    @Basic
    @Column(name = "recipient_id")
    private Integer recipientId;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;
}
