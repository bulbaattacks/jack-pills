package com.example.jackpills.controller;

import com.example.jackpills.dto.ScheduleDto;
import com.example.jackpills.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("/schedule")
    public List<ScheduleDto> getAllSchedule(){
        return service.getAllSchedule();
    }
}
