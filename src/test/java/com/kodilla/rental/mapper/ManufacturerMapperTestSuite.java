package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.dto.ManufacturerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ManufacturerMapperTestSuite {

    @InjectMocks
    private ManufacturerMapper manufacturerMapper;

    private Manufacturer manufacturer;
    private ManufacturerDto manufacturerDto;
    private final List<Manufacturer> manufacturerList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        manufacturer = new Manufacturer(1L, "test name", new HashSet<>());
        manufacturerDto = new ManufacturerDto(1L, "test name", new HashSet<>());
        manufacturerList.add(manufacturer);
    }

    @Test
    void mapToManufacturerTest() {
        //When
        Manufacturer expected = manufacturerMapper.mapToManufacturer(manufacturerDto);
        //Then
        assertEquals(1L, expected.getManufacturerId());
        assertEquals("test name", expected.getName());
    }

    @Test
    void mapToManufacturerDtoTest() {
        //When
        ManufacturerDto expected = manufacturerMapper.mapToManufacturerDto(manufacturer);
        //Then
        assertEquals(1L, expected.getManufacturerId());
        assertEquals("test name", expected.getName());
    }

    @Test
    void mapToManufacturerDtoListTest() {
        //When
        List<ManufacturerDto> expected = manufacturerMapper.mapToManufacturerDtoList(manufacturerList);
        //Then
        assertEquals(1, expected.size());
    }
}
