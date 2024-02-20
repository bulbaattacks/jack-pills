package com.example.jackpills.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "intervals")
public class Intervals {
    @Id
    @GeneratedValue( generator = "UUID")
    private UUID gid;
    @ManyToOne
    @JoinColumn(name = "type_gid")
    private TypeOfPills typeOfPills;
    @Column(name = "cold_months")
    private Integer coldMonths;
    @Column(name = "warm_months")
    private Integer warmMonths;
}
