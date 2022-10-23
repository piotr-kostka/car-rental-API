package com.kodilla.rental.service;

import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.dto.CarDto;
import com.kodilla.rental.exception.notFound.CarNotFoundException;
import com.kodilla.rental.mapper.CarMapper;
import com.kodilla.rental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarDbService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return carMapper.mapToCarDtoList(cars);
    }

    public CarDto getCar(final long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        return carMapper.mapToCarDto(car);
    }

    @Transactional
    public CarDto createCar(final CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        Car savedCar = carRepository.save(car);
        return carMapper.mapToCarDto(savedCar);
    }

    @Transactional
    public CarDto updateCar(final CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        Car savedCar = carRepository.save(car);
        return carMapper.mapToCarDto(savedCar);
    }

    @Transactional
    public void deleteCar(final long carId) {
        carRepository.deleteById(carId);
    }
}
