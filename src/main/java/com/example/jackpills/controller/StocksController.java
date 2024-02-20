package com.example.jackpills.controller;

import com.example.jackpills.entity.Stocks;
import com.example.jackpills.service.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StocksController {

    private final StocksService service;

    @GetMapping("/stocks")
    public List<Stocks> getAllStocks() {
        return service.getAllStocks();
    }
}
