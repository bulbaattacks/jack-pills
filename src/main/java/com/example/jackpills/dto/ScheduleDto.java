package com.example.jackpills.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ScheduleDto {

    private Date intakeDate;
    private Boolean isTaken;
    private String typeOfPillsType;
    private String pillName;
}
