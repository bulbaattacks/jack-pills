package com.example.jackpills.service.impl;

import com.example.jackpills.dao.IntervalsDao;
import com.example.jackpills.dao.PillsDao;
import com.example.jackpills.dao.StocksDao;
import com.example.jackpills.dao.TypeOfPillsDao;
import com.example.jackpills.dto.AddPillDto;
import com.example.jackpills.entity.Intervals;
import com.example.jackpills.entity.Pills;
import com.example.jackpills.entity.Stocks;
import com.example.jackpills.entity.TypeOfPills;
import com.example.jackpills.service.AddPillService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddPillServiceImpl implements AddPillService {

    private final ModelMapper mapper;

    private final TypeOfPillsDao typeOfPillsDao;
    private final IntervalsDao intervalsDao;
    private final PillsDao pillsDao;
    private final StocksDao stocksDao;

    @Transactional
    @Override
    public void addPillToStock(AddPillDto requestDto) {

        var typeOfPillsDto = requestDto.getTypeOfPillsDto();
        var savedTypeOfPills = getOrCreate(typeOfPillsDto);

        var intervalsDto = requestDto.getIntervalsDto();
        getOrCreate(savedTypeOfPills, intervalsDto);

        var pillsDto = requestDto.getPillsDto();
        var savedPill = getOrCreate(savedTypeOfPills, pillsDto);

        var stocksDto = requestDto.getStocksDto();
        getOrCreate(savedTypeOfPills, savedPill, stocksDto);
    }

    private TypeOfPills getOrCreate(AddPillDto.TypeOfPillsDto dto) {
        Optional<TypeOfPills> byType = typeOfPillsDao.findByType(dto.getType());
        TypeOfPills savedTypeOfPills;
        if (byType.isPresent()) {
            savedTypeOfPills = byType.get();
        } else {
            TypeOfPills typeOfPills = mapper.map(dto, TypeOfPills.class);
            savedTypeOfPills = typeOfPillsDao.save(typeOfPills);
        }
        return savedTypeOfPills;
    }

    private Intervals getOrCreate(TypeOfPills savedTypeOfPills, AddPillDto.IntervalsDto dto) {
        Optional<Intervals> optionalIntervals =
                intervalsDao.findByTypeOfPillsGidAndColdMonthsAndWarmMonths(savedTypeOfPills.getGid(), dto.getColdMonths(), dto.getWarmMonths());
        Intervals savedIntervals;
        if (optionalIntervals.isPresent()) {
            savedIntervals = optionalIntervals.get();
        } else {
            Intervals intervals = mapper.map(dto, Intervals.class);
            intervals.setTypeOfPills(savedTypeOfPills);
            savedIntervals = intervalsDao.save(intervals);
        }
        return savedIntervals;
    }

    private Pills getOrCreate(TypeOfPills savedTypeOfPills, AddPillDto.PillsDto dto) {
        Optional<Pills> byName = pillsDao.findByTypeOfPillsGidAndName(savedTypeOfPills.getGid(), dto.getName());
        Pills savedPill;
        if (byName.isPresent()) {
            savedPill = byName.get();
        } else {
            Pills pills = mapper.map(dto, Pills.class);
            pills.setTypeOfPills(savedTypeOfPills);
            savedPill = pillsDao.save(pills);
        }
        return savedPill;
    }

    private Stocks getOrCreate(TypeOfPills savedTypeOfPills, Pills savedPill, AddPillDto.StocksDto dto) {
        Optional<Stocks> byPillsGidAndQuantity = stocksDao.findByTypeOfPillsGidAndPillGidAndQuantity(savedTypeOfPills.getGid(), savedPill.getGid(), dto.getQuantity());
        Stocks savedStock;
        if (byPillsGidAndQuantity.isPresent()) {
            Integer allQuantity = byPillsGidAndQuantity.get().getQuantity() + dto.getQuantity();
            savedStock = byPillsGidAndQuantity.get();
            savedStock.setQuantity(allQuantity);
        } else {
            Stocks stock = mapper.map(dto, Stocks.class);
            stock.setTypeOfPills(savedTypeOfPills);
            stock.setPill(savedPill);
            savedStock = stocksDao.save(stock);
        }
        return savedStock;
    }
}
