package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.dto.CarDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CarMapperTestSuite {

    @InjectMocks
    private CarMapper carMapper;

    private Car car;
    private CarDto carDto;
    private List<Car> carList = new ArrayList<>();
    private Manufacturer manufacturer;
    private Model model;

    @BeforeEach
    void prepareData() {
        manufacturer = new Manufacturer(1L, "test name", new HashSet<>());
        model = new Model(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, new HashSet<>());
        car = new Car(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carDto = new CarDto(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carList.add(car);
    }

    @Test
    void mapToCarTest() {
        //When
        Car expected = carMapper.mapToCar(carDto);
        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void mapToCarDtoTest() {
        //When
        CarDto expected = carMapper.mapToCarDto(car);
        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void mapToCarDtoListTest() {
        //When
        List<CarDto> expected = carMapper.mapToCarDtoList(carList);
        //Then
        assertEquals(1, expected.size());
    }
}
