package com.example.jackpills.service.impl;

import com.example.jackpills.dao.IntervalsDao;
import com.example.jackpills.dao.ScheduleDao;
import com.example.jackpills.dao.StocksDao;
import com.example.jackpills.dao.TypeOfPillsDao;
import com.example.jackpills.dto.AddToScheduleDto;
import com.example.jackpills.entity.Intervals;
import com.example.jackpills.entity.Schedule;
import com.example.jackpills.entity.Stocks;
import com.example.jackpills.entity.TypeOfPills;
import com.example.jackpills.service.AddToScheduleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

@Service
@RequiredArgsConstructor
public class AddToScheduleImpl implements AddToScheduleService {

    private final ModelMapper mapper;

    private final TypeOfPillsDao typeOfPillsDao;
    private final ScheduleDao scheduleDao;
    private final StocksDao stocksDao;
    private final IntervalsDao intervalsDao;

    @Transactional
    @Override
    public void addToSchedule(AddToScheduleDto requestDto) {

        var typeOfPillsDto = requestDto.getTypeOfPills();
        String type = typeOfPillsDto.getType();
        Optional<TypeOfPills> optionalTypeOfPills = typeOfPillsDao.findByType(type);
        if (optionalTypeOfPills.isEmpty()) throw new IllegalArgumentException();

        List<Stocks> stocksList = stocksDao.findAllByTypeOfPillsGid(optionalTypeOfPills.get().getGid());
        if (stocksList.isEmpty()) throw new IllegalArgumentException();

        Optional<Intervals> optionalIntervals = intervalsDao.findByTypeOfPillsGid(optionalTypeOfPills.get().getGid());
        Integer coldIntervalDays = optionalIntervals.get().getColdMonths();
        Integer warmIntervalDays = optionalIntervals.get().getWarmMonths();
        Map<Month, Integer> monthIntegerMap = coldAndWarmMap(coldIntervalDays, warmIntervalDays);

        LocalDate intakeDate = requestDto.getIntakeDate();

        List<Schedule> schedulesToSave = new ArrayList<>();
        for (Stocks stocks: stocksList) {
            for (int i = 0; i < stocks.getQuantity(); i++) {

                Schedule schedule = new Schedule();
                schedule.setIntakeDate(intakeDate);
                schedule.setTypeOfPills(optionalTypeOfPills.get());
                schedule.setPill(stocks.getPill());
                schedule.setStocks(stocks);
                schedule.setIsTaken(Boolean.FALSE);

                schedulesToSave.add(schedule);

                intakeDate = calculateIntakeDate(intakeDate, monthIntegerMap);
            }
        }

        scheduleDao.saveAll(schedulesToSave);
    }

    private Map<Month, Integer> coldAndWarmMap (Integer forColdDays, Integer forWarmDays) {
        return Map.ofEntries(
                entry(Month.JANUARY, forColdDays),
                entry(Month.FEBRUARY, forColdDays),
                entry(Month.MARCH , forWarmDays),
                entry(Month.APRIL , forWarmDays),
                entry(Month.MAY , forWarmDays),
                entry(Month.JUNE , forWarmDays),
                entry(Month.JULY , forWarmDays),
                entry(Month.AUGUST , forWarmDays),
                entry(Month.SEPTEMBER , forWarmDays),
                entry(Month.OCTOBER , forColdDays),
                entry(Month.NOVEMBER , forColdDays),
                entry(Month.DECEMBER , forColdDays)
        );
    }

    private LocalDate calculateIntakeDate(LocalDate intakeDate, Map<Month, Integer> monthIntegerMap) {
        Integer interval = monthIntegerMap.get(intakeDate.getMonth());
        return intakeDate.plusDays(interval);
    }
}
