package com.kodilla.rental.service;

import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.dto.CarDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import com.kodilla.rental.mapper.CarMapper;
import com.kodilla.rental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTestSuite {

    @InjectMocks
    private CarDbService carService;

    @Mock
    private CarMapper carMapper;

    @Mock
    private CarRepository carRepository;

    private Car car;
    private CarDto carDto;
    private List<Car> carList = new ArrayList<>();
    private List<CarDto> carDtoList = new ArrayList<>();
    private Manufacturer manufacturer;
    private Model model;

    @BeforeEach
    void prepareData() {
        manufacturer = new Manufacturer(1L, "test name", new HashSet<>());
        model = new Model(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, new HashSet<>());
        car = new Car(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carDto = new CarDto(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carList.add(car);
        carDtoList.add(carDto);
    }

    @Test
    void getAllCarsTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getAllCars();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals(1L, expectedList.get(0).getCarId());
        assertEquals(model, expectedList.get(0).getModel());
        assertEquals("ST11111", expectedList.get(0).getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expectedList.get(0).getPrice());
        assertEquals(CarStatus.AVAILABLE, expectedList.get(0).getCarStatus());
    }

    @Test
    void getCarTest() {
        //Given
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);
        when(carRepository.findById(carDto.getCarId())).thenReturn(Optional.of(car));

        //When
        CarDto expected = carService.getCar(1L);

        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void getCarsByManufacturerTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getCarsByManufacturer(1L);

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void getCarsByModelTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getCarsByModel(1L);

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void getCarsByPriceLowerThanTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getCarsByPriceLowerThan(BigDecimal.valueOf(300));

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void getAvailableCarsTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getAvailableCars();

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void createCarTest() {
        //Given
        when(carMapper.mapToCar(carDto)).thenReturn(car);
        Car savedCar = carMapper.mapToCar(carDto);
        when(carRepository.save(car)).thenReturn(savedCar);
        when(carMapper.mapToCarDto(savedCar)).thenReturn(carDto);

        //When
        CarDto expected = carService.createCar(carDto);

        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void updateCarTest() {
        //Given
        when(carMapper.mapToCar(carDto)).thenReturn(car);
        Car savedCar = carMapper.mapToCar(carDto);
        when(carRepository.save(car)).thenReturn(savedCar);
        when(carMapper.mapToCarDto(savedCar)).thenReturn(carDto);
        when(carRepository.existsById(carDto.getCarId())).thenReturn(true);

        //When
        CarDto expected = carService.updateCar(carDto);

        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void deleteUserTest() {
        //Given
        when(carRepository.findById(carDto.getCarId())).thenReturn(Optional.of(car));

        //When
        carService.deleteCar(1L);

        //Then
        verify(carRepository, times(1)).deleteById(1L);
    }
}
