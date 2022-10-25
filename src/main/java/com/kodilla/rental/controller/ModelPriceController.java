package com.kodilla.rental.controller;

import com.kodilla.rental.domain.dto.ModelPriceDto;
import com.kodilla.rental.service.ModelPriceDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/model/prices")
@RequiredArgsConstructor
public class ModelPriceController {

    private final ModelPriceDbService priceDbService;

    @GetMapping
    public List<ModelPriceDto> getPrices() {
        return priceDbService.getModelPrices();
    }

    @GetMapping(value = "{priceId}")
    public ModelPriceDto getPrice(@PathVariable long priceId) {
        return priceDbService.getModelPrice(priceId);
    }

    @DeleteMapping(value = "{priceId}")
    public void deletePrice(@PathVariable long priceId) {
        priceDbService.deleteModelPrice(priceId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelPriceDto addPrice(@RequestBody ModelPriceDto priceDto){
        return priceDbService.createModelPrice(priceDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelPriceDto updatePrice(@RequestBody ModelPriceDto priceDto){
        return priceDbService.updateModelPrice(priceDto);
    }
}
