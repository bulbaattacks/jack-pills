package com.example.jackpills.service.impl;

import com.example.jackpills.dao.ScheduleDao;
import com.example.jackpills.dto.ScheduleDto;
import com.example.jackpills.entity.Schedule;
import com.example.jackpills.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao dao;
    private final ModelMapper mapper;

    @Override
    public List<ScheduleDto> getAllSchedule() {
        List<Schedule> pillsSchedule = dao.findAll();
        List<ScheduleDto> scheduleDtos = pillsSchedule.stream().map(schedule -> mapper.map(schedule, ScheduleDto.class)).toList();
        return scheduleDtos;
    }
}
