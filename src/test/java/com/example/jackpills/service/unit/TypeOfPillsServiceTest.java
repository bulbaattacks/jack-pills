package com.example.jackpills.service.unit;

import com.example.jackpills.dao.TypeOfPillsDao;
import com.example.jackpills.dto.TypeOfPillsDto;
import com.example.jackpills.entity.TypeOfPills;
import com.example.jackpills.service.impl.TypeOfPillsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@DisplayName("Класс TypeOfPillsService должен")
@ExtendWith(MockitoExtension.class)
class TypeOfPillsServiceTest {

    @Spy
    private ModelMapper mapper;

    @Mock
    private TypeOfPillsDao dao;

    @InjectMocks
    private TypeOfPillsServiceImpl service;

    @DisplayName("должен вернуть тип таблеток")
    @Test
    void getAllTypes_shouldReturnTypeCorrectly() {
        TypeOfPills typeOfPills = new TypeOfPills();
        typeOfPills.setType("yyyy");

        List<TypeOfPills> entityList = List.of(typeOfPills);
        Mockito.when(dao.findAll()).thenReturn(entityList);

        List<TypeOfPillsDto> allTypes = service.getAllTypes();

        Assertions.assertThat(allTypes).isNotEmpty();
        TypeOfPillsDto typeOfPillsDto = allTypes.get(0);
        Assertions.assertThat(typeOfPillsDto).isNotNull();
        Assertions.assertThat(typeOfPillsDto.getType()).isEqualTo(typeOfPills.getType());
    }
}