package com.kodilla.rental.controller;

import com.kodilla.rental.domain.dto.CarDto;
import com.kodilla.rental.service.CarDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/cars")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CarController {

    private final CarDbService carDbService;

    @GetMapping
    public List<CarDto> getCars() {
        return carDbService.getAllCars();
    }

    @GetMapping(value = "{carId}")
    public CarDto getCar(@PathVariable long carId) {
        return carDbService.getCar(carId);
    }

    @GetMapping(value = "/cars/manufacturer/{manufacturerId}")
    public List<CarDto> getCarsByManufacturer(@PathVariable long manufacturerId) {
        return carDbService.getCarsByManufacturer(manufacturerId);
    }

    @GetMapping(value = "/cars/model/{modelId}")
    public List<CarDto> getCarsByModel(@PathVariable long modelId) {
        return carDbService.getCarsByModel(modelId);
    }

    @GetMapping(value = "/cars/price/{price}")
    public List<CarDto> getCarsByPriceLowerThan(@PathVariable BigDecimal price) {
        return carDbService.getCarsByPriceLowerThan(price);
    }

    @GetMapping(value = "/cars/available")
    public List<CarDto> getAvailableCars() {
        return carDbService.getAvailableCars();
    }

    @GetMapping(value = "/cars/rented")
    public List<CarDto> getRentedCars() {
        return carDbService.getRentedCars();
    }

    @DeleteMapping(value = "{carId}")
    public void deleteCar(@PathVariable long carId) {
        carDbService.deleteCar(carId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CarDto addCar(@RequestBody CarDto carDto){
        return carDbService.createCar(carDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CarDto updateCar(@RequestBody CarDto carDto){
        return carDbService.updateCar(carDto);
    }
}
