package com.kodilla.rental.service;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.dto.ModelDto;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import com.kodilla.rental.mapper.ModelMapper;
import com.kodilla.rental.repository.ModelRepository;
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
public class ModelServiceTestSuite {

    @InjectMocks
    private ModelDbService modelService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ModelRepository modelRepository;

    private Model model;
    private ModelDto modelDto;
    private final List<Model> modelList = new ArrayList<>();
    private final List<ModelDto> modelDtoList = new ArrayList<>();
    private Manufacturer manufacturer;

    @BeforeEach
    void prepareData() {
        manufacturer = new Manufacturer(1L, "test name", new HashSet<>());
        model = new Model(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, new HashSet<>());
        modelDto = new ModelDto(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, new HashSet<>());
        modelList.add(model);
        modelDtoList.add(modelDto);
    }

    @Test
    void getAllModelsTest() {
        //Given
        when(modelMapper.mapToModelDtoList(modelList)).thenReturn(modelDtoList);
        when(modelRepository.findAll()).thenReturn(modelList);

        //When
        List<ModelDto> expectedList = modelService.getModels();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals(1L, expectedList.get(0).getModelId());
        assertEquals(manufacturer, expectedList.get(0).getManufacturer());
        assertEquals("name", expectedList.get(0).getName());
        assertEquals(1800.00, expectedList.get(0).getEngineSize());
        assertEquals(BodyType.SEDAN, expectedList.get(0).getBodyType());
        assertEquals(2012, expectedList.get(0).getProductionYear());
        assertEquals("red", expectedList.get(0).getColor());
        assertEquals(5, expectedList.get(0).getSeatsQuantity());
        assertEquals(4, expectedList.get(0).getDoorQuantity());
        assertEquals(FuelType.DIESEL, expectedList.get(0).getFuelType());
        assertEquals(TransmissionType.AUTOMATIC, expectedList.get(0).getTransmissionType());
    }

    @Test
    void getModelTest() {
        //Given
        when(modelMapper.mapToModelDto(model)).thenReturn(modelDto);
        when(modelRepository.findById(modelDto.getModelId())).thenReturn(Optional.of(model));

        //When
        ModelDto expected = modelService.getModel(1L);

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
    void createModelTest() {
        //Given
        when(modelMapper.mapToModel(modelDto)).thenReturn(model);
        Model savedModel = modelMapper.mapToModel(modelDto);
        when(modelRepository.save(model)).thenReturn(savedModel);
        when(modelMapper.mapToModelDto(savedModel)).thenReturn(modelDto);

        //When
        ModelDto expected = modelService.createModel(modelDto);

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
    void updateModelTest() {
        //Given
        when(modelMapper.mapToModel(modelDto)).thenReturn(model);
        Model savedModel = modelMapper.mapToModel(modelDto);
        when(modelRepository.save(model)).thenReturn(savedModel);
        when(modelMapper.mapToModelDto(savedModel)).thenReturn(modelDto);
        when(modelRepository.existsById(modelDto.getModelId())).thenReturn(true);

        //When
        ModelDto expected = modelService.updateModel(modelDto);

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
    void deleteUserTest() {
        //Given
        when(modelRepository.findById(modelDto.getModelId())).thenReturn(Optional.of(model));

        //When
        modelService.deleteModel(1L);

        //Then
        verify(modelRepository, times(1)).deleteById(1L);
    }
}
