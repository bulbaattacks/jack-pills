package com.example.jackpills.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue( generator = "UUID")
    private UUID gid;
    @Column(name = "intake_date")
    private LocalDate intakeDate;
    @ManyToOne
    @JoinColumn(name = "type_gid")
    private TypeOfPills typeOfPills;
    @ManyToOne
    @JoinColumn(name = "pill_gid")
    private Pills pill;
    @ManyToOne
    @JoinColumn(name = "stock_gid")
    private Stocks stocks;
    @Column(name = "is_taken")
    private Boolean isTaken;
}
