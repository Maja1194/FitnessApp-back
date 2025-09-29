package com.example.onlinefitnessback.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "attributehasvalue", schema = "fitnesprogram", catalog = "")
public class AttributehasvalueEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "value")
    private String value;
    @ManyToOne
    @JoinColumn(name = "Attribute_id", referencedColumnName = "id", nullable = false)
    private AttributeEntity attribute;

}
