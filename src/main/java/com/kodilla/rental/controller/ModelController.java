package com.kodilla.rental.controller;

import com.kodilla.rental.domain.dto.ModelDto;
import com.kodilla.rental.service.ModelDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/models")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ModelController {

    private final ModelDbService modelDbService;

    @GetMapping
    public List<ModelDto> getModels() {
        return modelDbService.getModels();
    }

    @GetMapping(value = "{modelId}")
    public ModelDto getModel(@PathVariable long modelId) {
        return modelDbService.getModel(modelId);
    }

    @DeleteMapping(value = "{modelId}")
    public void deleteModel(@PathVariable long modelId) {
        modelDbService.deleteModel(modelId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelDto addModel(@RequestBody ModelDto modelDto){
        return modelDbService.createModel(modelDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelDto updateModel(@RequestBody ModelDto modelDto){
        return modelDbService.updateModel(modelDto);
    }
}
