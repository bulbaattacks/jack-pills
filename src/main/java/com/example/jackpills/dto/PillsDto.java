package com.example.jackpills.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PillsDto {
    private TypeOfPillsDto typeOfPills;
    private String name;

    @Getter
    @Setter
    public static class TypeOfPillsDto {
        private String type;
    }
}