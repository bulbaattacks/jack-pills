package com.example.jackpills.controller;

import com.example.jackpills.dto.AddToScheduleDto;
import com.example.jackpills.service.AddToScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddToScheduleController {

    private final AddToScheduleService service;

    @PostMapping("add_to_schedule")
    public void addToSchedule(@RequestBody AddToScheduleDto requestDto) {
        service.addToSchedule(requestDto);

    }


}
