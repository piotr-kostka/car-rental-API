package com.kodilla.rental.service;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.dto.ManufacturerDto;
import com.kodilla.rental.mapper.ManufacturerMapper;
import com.kodilla.rental.repository.ManufacturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceTestSuite {

    @InjectMocks
    private ManufacturerDbService manufacturerService;

    @Mock
    private ManufacturerMapper manufacturerMapper;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    private Manufacturer manufacturer;
    private ManufacturerDto manufacturerDto;
    private final List<Manufacturer> manufacturerList = new ArrayList<>();
    private final List<ManufacturerDto> manufacturerDtoList = new ArrayList<>();


    @BeforeEach
    void prepareData() {
        manufacturer = new Manufacturer(1L, "test name", new HashSet<>());
        manufacturerDto = new ManufacturerDto(1L, "test name", new HashSet<>());
        manufacturerList.add(manufacturer);
        manufacturerDtoList.add(manufacturerDto);
    }

    @Test
    void getAllManufacturersTest() {
        //Given
        when(manufacturerMapper.mapToManufacturerDtoList(manufacturerList)).thenReturn(manufacturerDtoList);
        when(manufacturerRepository.findAll()).thenReturn(manufacturerList);

        //When
        List<ManufacturerDto> expectedList = manufacturerService.getAllManufacturers();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals("test name", expectedList.get(0).getName());
    }

    @Test
    void getManufacturerTest() {
        //Given
        when(manufacturerMapper.mapToManufacturerDto(manufacturer)).thenReturn(manufacturerDto);
        when(manufacturerRepository.findById(manufacturerDto.getManufacturerId())).thenReturn(Optional.of(manufacturer));

        //When
        ManufacturerDto expected = manufacturerService.getManufacturer(1L);

        //Then
        assertEquals("test name", expected.getName());
    }

    @Test
    void createManufacturerTest() {
        //Given
        when(manufacturerMapper.mapToManufacturer(manufacturerDto)).thenReturn(manufacturer);
        Manufacturer savedManufacturer = manufacturerMapper.mapToManufacturer(manufacturerDto);
        when(manufacturerRepository.save(manufacturer)).thenReturn(savedManufacturer);
        when(manufacturerMapper.mapToManufacturerDto(savedManufacturer)).thenReturn(manufacturerDto);

        //When
        ManufacturerDto expected = manufacturerService.createManufacturer(manufacturerDto);

        //Then
        assertEquals(1L, expected.getManufacturerId());
        assertEquals("test name", expected.getName());
    }

    @Test
    void updateManufacturerTest() {
        //Given
        when(manufacturerMapper.mapToManufacturer(manufacturerDto)).thenReturn(manufacturer);
        Manufacturer savedManufacturer = manufacturerMapper.mapToManufacturer(manufacturerDto);
        when(manufacturerRepository.save(manufacturer)).thenReturn(savedManufacturer);
        when(manufacturerMapper.mapToManufacturerDto(savedManufacturer)).thenReturn(manufacturerDto);
        when(manufacturerRepository.existsById(manufacturerDto.getManufacturerId())).thenReturn(true);

        //When
        ManufacturerDto expected = manufacturerService.updateManufacturer(manufacturerDto);

        //Then
        assertEquals(1L, expected.getManufacturerId());
        assertEquals("test name", expected.getName());
    }

    @Test
    void deleteManufacturerTest() {
        //Given
        when(manufacturerRepository.findById(manufacturerDto.getManufacturerId())).thenReturn(Optional.of(manufacturer));

        //When
        manufacturerService.deleteManufacturer(1L);

        //Then
        verify(manufacturerRepository, times(1)).deleteById(1L);
    }
}
