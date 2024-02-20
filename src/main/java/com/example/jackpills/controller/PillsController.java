package com.example.jackpills.controller;

import com.example.jackpills.dto.PillsDto;
import com.example.jackpills.service.PillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PillsController {

    private final PillsService service;

    @GetMapping("/pills")
    public List<PillsDto> getAllPills(){
        return service.getAllPills();
    }
}
