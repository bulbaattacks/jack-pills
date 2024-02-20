package com.example.jackpills.service.impl;

import com.example.jackpills.dao.StocksDao;
import com.example.jackpills.entity.Stocks;
import com.example.jackpills.service.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {

    private final StocksDao dao;

    @Override
    public List<Stocks> getAllStocks() {
        List<Stocks> stocks = dao.findAll();
        return stocks;
    }
}
