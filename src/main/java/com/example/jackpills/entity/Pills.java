package com.example.jackpills.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pills")
public class Pills {
    @Id
    @GeneratedValue( generator = "UUID")
    private UUID gid;
    @ManyToOne
    @JoinColumn(name = "type_gid")
    private TypeOfPills typeOfPills;
    @Column(name = "name")
    private String name;
}
