package com.example.jackpills.service.impl;

import com.example.jackpills.dao.PillsDao;
import com.example.jackpills.dto.PillsDto;
import com.example.jackpills.entity.Pills;
import com.example.jackpills.service.PillsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PillsServiceImpl implements PillsService {

    private final PillsDao dao;
    private final ModelMapper mapper;

    @Override
    public List<PillsDto> getAllPills() {
        List<Pills> pills = dao.findAll();
        List<PillsDto> pillsDtos = pills.stream().map(pill -> mapper.map(pill, PillsDto.class)).toList();
        return pillsDtos;
    }
}
