package com.example.jackpills.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddPillDto {
    private TypeOfPillsDto typeOfPillsDto;
    private IntervalsDto intervalsDto;
    private PillsDto pillsDto;
    private StocksDto stocksDto;

    @Getter
    @Setter
    public static class TypeOfPillsDto {
        private String type;
    }

    @Getter
    @Setter
    public static class IntervalsDto {
        private Integer coldMonths;
        private Integer warmMonths;
    }

    @Getter
    @Setter
    public static class PillsDto {
        private String name;
    }

    @Getter
    @Setter
    public static class StocksDto {
        private LocalDate dueDate;
        private Integer quantity;
    }
}
