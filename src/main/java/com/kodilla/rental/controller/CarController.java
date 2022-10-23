package com.kodilla.rental.controller;

import com.kodilla.rental.domain.dto.CarDto;
import com.kodilla.rental.service.CarDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/cars")
@RequiredArgsConstructor
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

    @DeleteMapping(value = "{carId}")
    public void deleteCar(@PathVariable long carId) {
        carDbService.deleteCar(carId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CarDto createCar(@RequestBody CarDto carDto){
        return carDbService.createCar(carDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CarDto updateCar(@RequestBody CarDto carDto){
        return carDbService.updateCar(carDto);
    }
}

//getByManufacturer
//getByModel
//getByPrice
//getAvailable
//getRented