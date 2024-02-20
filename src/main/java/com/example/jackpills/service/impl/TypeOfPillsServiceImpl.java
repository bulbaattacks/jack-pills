package com.example.jackpills.service.impl;

import com.example.jackpills.dao.TypeOfPillsDao;
import com.example.jackpills.dto.TypeOfPillsDto;
import com.example.jackpills.entity.TypeOfPills;
import com.example.jackpills.service.TypeOfPillsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfPillsServiceImpl implements TypeOfPillsService {

    private final TypeOfPillsDao dao;
    private final ModelMapper mapper;

    @Override
    public List<TypeOfPillsDto> getAllTypes() {
        List<TypeOfPills> entityList = dao.findAll();
        return entityList.stream().map(e -> mapper.map(e, TypeOfPillsDto.class)).toList();
    }
}