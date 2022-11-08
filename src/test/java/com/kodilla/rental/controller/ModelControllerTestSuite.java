package com.kodilla.rental.controller;

import com.google.gson.Gson;
import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.dto.ModelDto;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import com.kodilla.rental.service.ModelDbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(ModelController.class)
public class ModelControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelDbService service;

    private ModelDto modelDto;
    private final List<ModelDto> modelDtoList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        Manufacturer manufacturer = new Manufacturer(1L, "test name", null);
        modelDto = new ModelDto(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, null);
        modelDtoList.add(modelDto);
    }

    @Test
    void shouldGetModelsTest() throws Exception {
        //Given
        when(service.getModels()).thenReturn(modelDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/models")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].modelId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transmissionType", Matchers.is("AUTOMATIC")));
    }

    @Test
    void shouldGetModelTest() throws Exception {
        //Given
        when(service.getModel(1L)).thenReturn(modelDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/models/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transmissionType", Matchers.is("AUTOMATIC")));
    }

    @Test
    void shouldDeleteModelTest() throws Exception {
        //Given
        when(service.getModel(1L)).thenReturn(modelDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/models/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddModelTest() throws Exception {
        //Given
        when(service.createModel(any(ModelDto.class))).thenReturn(modelDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(modelDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/models")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transmissionType", Matchers.is("AUTOMATIC")));
    }

    @Test
    void shouldUpdateModelTest() throws Exception {
        //Given
        when(service.updateModel(any(ModelDto.class))).thenReturn(modelDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(modelDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/models")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transmissionType", Matchers.is("AUTOMATIC")));
    }
}
