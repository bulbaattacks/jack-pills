package com.example.jackpills.service.unit;

import com.example.jackpills.dao.IntervalsDao;
import com.example.jackpills.dao.PillsDao;
import com.example.jackpills.dao.StocksDao;
import com.example.jackpills.dao.TypeOfPillsDao;
import com.example.jackpills.dto.AddPillDto;
import com.example.jackpills.entity.Intervals;
import com.example.jackpills.entity.Pills;
import com.example.jackpills.entity.Stocks;
import com.example.jackpills.entity.TypeOfPills;
import com.example.jackpills.service.impl.AddPillServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("Класс AddPillService должен")
@ExtendWith(MockitoExtension.class)
class AddPillServiceTest {

    @Spy
    private ModelMapper mapper;

    @Mock
    private TypeOfPillsDao typeOfPillsDao;
    @Mock
    private IntervalsDao intervalsDao;
    @Mock
    private PillsDao pillsDao;
    @Mock
    private StocksDao stocksDao;

    @InjectMocks
    private AddPillServiceImpl service;

    @Disabled
    @DisplayName("вызывать dao по одному разу")
    @Test
    void addPillToStock_shouldCallEachDbOnce() {

        var dto = createAddPillDto();

        service.addPillToStock(dto);

        Mockito.verify(typeOfPillsDao, Mockito.times(1)).save(any());
        Mockito.verify(intervalsDao, Mockito.times(1)).save(any());
        Mockito.verify(pillsDao, Mockito.times(1)).save(any());
        Mockito.verify(stocksDao, Mockito.times(1)).save(any());
    }

    @Disabled
    @DisplayName("сохранять корректно данные")
    @Test
    void addPillToStock_shouldSaveEntityInDbCorrectly() {

        var dto = createAddPillDto();

        var typeOfPillsCaptor = ArgumentCaptor.forClass(TypeOfPills.class);
        var intervalsCaptor = ArgumentCaptor.forClass(Intervals.class);
        var pillsCaptor = ArgumentCaptor.forClass(Pills.class);
        var stocksCaptor = ArgumentCaptor.forClass(Stocks.class);

        Mockito.when(typeOfPillsDao.findByType(any())).thenReturn(Optional.of(new TypeOfPills()));
        Mockito.when(pillsDao.findByTypeOfPillsGidAndName(any(), any())).thenReturn(Optional.of(new Pills()));

        service.addPillToStock(dto);

        Mockito.verify(typeOfPillsDao).save(typeOfPillsCaptor.capture());
        Mockito.verify(intervalsDao).save(intervalsCaptor.capture());
        Mockito.verify(pillsDao).save(pillsCaptor.capture());
        Mockito.verify(stocksDao).save(stocksCaptor.capture());

        TypeOfPills typeOfPillsValue = typeOfPillsCaptor.getValue();
        Intervals intervalsValue = intervalsCaptor.getValue();
        Pills pillsValue = pillsCaptor.getValue();
        Stocks stocksValue = stocksCaptor.getValue();

        Assertions.assertThat(typeOfPillsValue.getType()).isEqualTo(dto.getTypeOfPillsDto().getType());
        Assertions.assertThat(intervalsValue.getColdMonths()).isEqualTo(dto.getIntervalsDto().getColdMonths());
        Assertions.assertThat(intervalsValue.getWarmMonths()).isEqualTo(dto.getIntervalsDto().getWarmMonths());
        Assertions.assertThat(pillsValue.getName()).isEqualTo(dto.getPillsDto().getName());
        Assertions.assertThat(stocksValue.getQuantity()).isEqualTo(dto.getStocksDto().getQuantity());

    }

    private AddPillDto createAddPillDto() {
        var typeOfPillsDto = new AddPillDto.TypeOfPillsDto();
        typeOfPillsDto.setType("от простуды");

        var intervalsDto = new AddPillDto.IntervalsDto();
        intervalsDto.setColdMonths(7);
        intervalsDto.setWarmMonths(6);

        var pillsDto = new AddPillDto.PillsDto();
        pillsDto.setName("Ahuin");

        var stocksDto = new AddPillDto.StocksDto();
        stocksDto.setQuantity(3);
        stocksDto.setDueDate(LocalDate.parse("2026-01-09"));

        var dto = new AddPillDto();
        dto.setTypeOfPillsDto(typeOfPillsDto);
        dto.setIntervalsDto(intervalsDto);
        dto.setPillsDto(pillsDto);
        dto.setStocksDto(stocksDto);

        return dto;
    }
}
