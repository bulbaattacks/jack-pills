package com.example.jackpills.service;

import com.example.jackpills.dto.AddPillDto;

public interface AddPillService {

    void addPillToStock(AddPillDto requestDto);
}
