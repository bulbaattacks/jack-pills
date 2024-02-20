package com.example.jackpills.controller;

import com.example.jackpills.dto.AddPillDto;
import com.example.jackpills.service.AddPillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddPillController {

    private final AddPillService service;

    @PostMapping("/add_pill_to_stock")
    public void addPillToStock(@RequestBody AddPillDto requestDto) {
        service.addPillToStock(requestDto);
    }

    @GetMapping("/pills2")
    public AddPillDto test() {
        AddPillDto addPillDto = new AddPillDto();
        var typeOfPillsDto = new AddPillDto.TypeOfPillsDto();
        addPillDto.setTypeOfPillsDto(typeOfPillsDto);

        var pillsDto = new AddPillDto.PillsDto();
        addPillDto.setPillsDto(pillsDto);

        var stocksDto = new AddPillDto.StocksDto();
        addPillDto.setStocksDto(stocksDto);

        var intDto = new AddPillDto.IntervalsDto();
        addPillDto.setIntervalsDto(intDto);
        return addPillDto;
    }
}
