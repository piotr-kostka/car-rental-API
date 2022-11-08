package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.dto.ModelDto;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
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
public class ModelMapperTestSuite {

    @InjectMocks
    private ModelMapper modelMapper;

    private Model model;
    private ModelDto modelDto;
    private final List<Model> modelList = new ArrayList<>();
    private Manufacturer manufacturer;

    @BeforeEach
    void prepareData() {
        manufacturer = new Manufacturer(1L, "test name", new HashSet<>());
        model = new Model(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, new HashSet<>());
        modelDto = new ModelDto(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, new HashSet<>());
        modelList.add(model);
    }

    @Test
    void mapToModelTest() {
        //When
        Model expected = modelMapper.mapToModel(modelDto);
        //Then
        assertEquals(1L, expected.getModelId());
        assertEquals(manufacturer, expected.getManufacturer());
        assertEquals("name", expected.getName());
        assertEquals(1800.00, expected.getEngineSize());
        assertEquals(BodyType.SEDAN, expected.getBodyType());
        assertEquals(2012, expected.getProductionYear());
        assertEquals("red", expected.getColor());
        assertEquals(5, expected.getSeatsQuantity());
        assertEquals(4, expected.getDoorQuantity());
        assertEquals(FuelType.DIESEL, expected.getFuelType());
        assertEquals(TransmissionType.AUTOMATIC, expected.getTransmissionType());
    }

    @Test
    void mapToModelDtoTest() {
        //When
        ModelDto expected = modelMapper.mapToModelDto(model);
        //Then
        assertEquals(1L, expected.getModelId());
        assertEquals(manufacturer, expected.getManufacturer());
        assertEquals("name", expected.getName());
        assertEquals(1800.00, expected.getEngineSize());
        assertEquals(BodyType.SEDAN, expected.getBodyType());
        assertEquals(2012, expected.getProductionYear());
        assertEquals("red", expected.getColor());
        assertEquals(5, expected.getSeatsQuantity());
        assertEquals(4, expected.getDoorQuantity());
        assertEquals(FuelType.DIESEL, expected.getFuelType());
        assertEquals(TransmissionType.AUTOMATIC, expected.getTransmissionType());
    }

    @Test
    void mapToModelDtoListTest() {
        //When
        List<ModelDto> expected = modelMapper.mapToModelDtoList(modelList);
        //Then
        assertEquals(1, expected.size());
    }
}
