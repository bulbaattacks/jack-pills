package com.example.jackpills.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "type_of_pills")
public class TypeOfPills {
    @Id
    @GeneratedValue( generator = "UUID")
    private UUID gid;
    @Column(name = "type")
    private String type;
}