package com.example.jackpills.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddToScheduleDto {
    private LocalDate intakeDate;
    private TypeOfPillsDto typeOfPills;

    @Getter
    @Setter
    public static class TypeOfPillsDto {
        private String type;
    }
}