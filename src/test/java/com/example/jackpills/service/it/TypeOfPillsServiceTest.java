package com.example.jackpills.service.it;

import com.example.jackpills.config.TestConfiguration;
import com.example.jackpills.dao.TypeOfPillsDao;
import com.example.jackpills.dto.TypeOfPillsDto;
import com.example.jackpills.entity.TypeOfPills;
import com.example.jackpills.service.TypeOfPillsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DisplayName("Класс TypeOfPillsService должен")
@DataJpaTest
@Import({TestConfiguration.class})
class TypeOfPillsServiceTest {

    @Autowired
    private TypeOfPillsDao dao;

    @Autowired
    private TypeOfPillsService service;

    private TypeOfPills pillType;

    @BeforeEach
    void setUp() {
        pillType = new TypeOfPills();
        pillType.setType("от блох");
        dao.save(pillType);
    }

    @DisplayName("должен вернуть правильный тип таблеток")
    @Test
    void getAllTypes_shouldReturnTypeCorrectly() {
        List<TypeOfPillsDto> allTypes = service.getAllTypes();
        Assertions.assertThat(allTypes).isNotEmpty();
        TypeOfPillsDto dto = allTypes.get(0);
        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getType()).isEqualTo(pillType.getType()).isNotNull();
    }

    @DisplayName("проверить размер List<TypeOfPillsDto>")
    @Test
    void getAllTypes_shouldReturnTypeCorrectly_2() {
        List<TypeOfPillsDto> allTypes = service.getAllTypes();
        Assertions.assertThat(allTypes).hasSize(1);

    }
}
