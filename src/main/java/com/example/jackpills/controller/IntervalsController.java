package com.example.jackpills.controller;

import com.example.jackpills.entity.Intervals;
import com.example.jackpills.service.IntervalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntervalsController{

    private final IntervalsService service;

    @GetMapping("/intervals")
    public List<Intervals> getAllIntervals() {
        return service.getAllIntervals();
    }
}
