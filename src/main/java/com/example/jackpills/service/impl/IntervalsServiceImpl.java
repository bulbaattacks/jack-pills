package com.example.jackpills.service.impl;

import com.example.jackpills.dao.IntervalsDao;
import com.example.jackpills.entity.Intervals;
import com.example.jackpills.service.IntervalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntervalsServiceImpl implements IntervalsService {

    private final IntervalsDao dao;

    @Override
    public List<Intervals> getAllIntervals() {
        List<Intervals> intervals = dao.findAll();
        return intervals;
    }
}
