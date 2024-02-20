package com.example.jackpills.service.it;

import com.example.jackpills.config.TestConfiguration;
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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

@DisplayName("Класс AddPillService должен")
@DataJpaTest
@Import({TestConfiguration.class})
class AddPillServiceTest {

    @Autowired
    private AddPillService service;

    @Autowired
    private TypeOfPillsDao typeOfPillsDao;

    @Autowired
    private IntervalsDao intervalsDao;

    @Autowired
    private PillsDao pillsDao;

    @Autowired
    private StocksDao stocksDao;

    private AddPillDto dto;

    @BeforeEach
    void setUp() {
        dto = createAddPillDto();
    }

    @DisplayName("добавлять новую таблетку в тип, интервалы, препарат и запас")
    @Test
    void addNewPillToStock_shouldSaveToFourTables() {
        service.addPillToStock(dto);

        List<TypeOfPills> allTypeOfPills = typeOfPillsDao.findAll();
        List<Intervals> allIntervals = intervalsDao.findAll();
        List<Pills> allPills = pillsDao.findAll();
        List<Stocks> allStocks = stocksDao.findAll();

        Assertions.assertThat(allTypeOfPills).hasSize(1);
        Assertions.assertThat(allTypeOfPills.get(0).getGid()).isNotNull();
        Assertions.assertThat(allTypeOfPills.get(0).getType()).isEqualTo(dto.getTypeOfPillsDto().getType()).isNotNull();

        Assertions.assertThat(allIntervals).hasSize(1);
        Assertions.assertThat(allIntervals.get(0).getGid()).isNotNull();
        Assertions.assertThat(allIntervals.get(0).getWarmMonths()).isEqualTo(dto.getIntervalsDto().getWarmMonths()).isNotNull();
        Assertions.assertThat(allIntervals.get(0).getColdMonths()).isEqualTo(dto.getIntervalsDto().getColdMonths()).isNotNull();

        Assertions.assertThat(allPills).hasSize(1);
        Assertions.assertThat(allPills.get(0).getGid()).isNotNull();
        Assertions.assertThat(allPills.get(0).getName()).isEqualTo(dto.getPillsDto().getName());

        Assertions.assertThat(allStocks).hasSize(1);
        Assertions.assertThat(allStocks.get(0).getGid()).isNotNull();
        Assertions.assertThat(allStocks.get(0).getQuantity()).isEqualTo(dto.getStocksDto().getQuantity());
    }

    @DisplayName("увеличивать количество таблеток, которые уже есть в запасе")
    @Test
    void addExistingPillToStock_shoulUpdateQuantityInStockTable() {
        service.addPillToStock(dto);
        service.addPillToStock(dto);
        var targetQuantity = dto.getStocksDto().getQuantity() * 2;

        List<TypeOfPills> allTypeOfPills = typeOfPillsDao.findAll();
        List<Intervals> allIntervals = intervalsDao.findAll();
        List<Pills> allPills = pillsDao.findAll();
        List<Stocks> allStocks = stocksDao.findAll();

        Assertions.assertThat(allTypeOfPills).hasSize(1);
        Assertions.assertThat(allIntervals).hasSize(1);
        Assertions.assertThat(allPills).hasSize(1);
        Assertions.assertThat(allStocks).hasSize(1);
        Assertions.assertThat(allStocks.get(0).getQuantity()).isEqualTo(targetQuantity);
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
