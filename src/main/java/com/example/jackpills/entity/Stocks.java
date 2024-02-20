package com.example.jackpills.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "stocks")
public class Stocks {

    @Id
    @GeneratedValue( generator = "UUID")
    private UUID gid;
    @ManyToOne
    @JoinColumn(name = "pill_gid")
    private Pills pill;
    @ManyToOne
    @JoinColumn(name = "type_gid")
    private TypeOfPills typeOfPills;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "quantity")
    private Integer quantity;
}
