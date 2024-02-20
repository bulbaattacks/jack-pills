package com.example.jackpills.controller;

import com.example.jackpills.dto.TypeOfPillsDto;
import com.example.jackpills.service.TypeOfPillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TypeOfPillsController {

    private final TypeOfPillsService service;

    @GetMapping("/type_of_pills")
    public List<TypeOfPillsDto> getAllTypes() {
        return service.getAllTypes();
    }
}
