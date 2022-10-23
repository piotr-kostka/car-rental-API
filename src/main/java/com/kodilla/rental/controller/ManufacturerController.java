package com.kodilla.rental.controller;

import com.kodilla.rental.domain.dto.ManufacturerDto;
import com.kodilla.rental.service.ManufacturerDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerDbService manufacturerDbService;

    @GetMapping
    public List<ManufacturerDto> getManufacturers() {
        return manufacturerDbService.getAllManufacturers();
    }

    @GetMapping(value = "{manufacturerId}")
    public ManufacturerDto getManufacturer(@PathVariable long manufacturerId) {
        return manufacturerDbService.getManufacturer(manufacturerId);
    }

    @DeleteMapping(value = "{manufacturerId}")
    public void deleteManufacturer(@PathVariable long manufacturerId) {
        manufacturerDbService.deleteManufacturer(manufacturerId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ManufacturerDto createManufacturer(@RequestBody ManufacturerDto manufacturerDto){
        return manufacturerDbService.createManufacturer(manufacturerDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ManufacturerDto updateManufacturer(@RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerDbService.updateManufacturer(manufacturerDto);
    }
}
