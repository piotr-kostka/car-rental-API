package com.kodilla.rental.service;

import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.dto.CarDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.exception.notFound.CarNotFoundException;
import com.kodilla.rental.exception.notFound.ManufacturerNotFoundException;
import com.kodilla.rental.mapper.CarMapper;
import com.kodilla.rental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CarDto> getCarsByManufacturer(final long manufacturerId) throws ManufacturerNotFoundException {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getModel().getManufacturer().getManufacturerId() == manufacturerId)
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarDto> getCarsByModel(final long modelId) throws ManufacturerNotFoundException {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getModel().getModelId() == modelId)
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarDto> getCarsByPriceLowerThan(final BigDecimal price) {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getPrice().compareTo(price) <= 0)
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarDto> getAvailableCars() {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getCarStatus().equals(CarStatus.AVAILABLE))
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarDto> getRentedCars() {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getCarStatus().equals(CarStatus.RENTED))
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
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
