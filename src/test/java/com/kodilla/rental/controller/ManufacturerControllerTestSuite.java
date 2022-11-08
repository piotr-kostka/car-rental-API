package com.kodilla.rental.controller;

import com.google.gson.Gson;
import com.kodilla.rental.domain.dto.ManufacturerDto;
import com.kodilla.rental.service.ManufacturerDbService;
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
@WebMvcTest(ManufacturerController.class)
public class ManufacturerControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManufacturerDbService service;

    private ManufacturerDto manufacturerDto;
    private final List<ManufacturerDto> manufacturerDtoList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        manufacturerDto = new ManufacturerDto(1L, "test name", null);
        manufacturerDtoList.add(manufacturerDto);
    }

    @Test
    void shouldGetManufacturersTest() throws Exception {
        //Given
        when(service.getAllManufacturers()).thenReturn(manufacturerDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/manufacturers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test name")));
    }

    @Test
    void shouldGetManufacturerTest() throws Exception {
        //Given
        when(service.getManufacturer(1L)).thenReturn(manufacturerDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/manufacturers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name")));
    }

    @Test
    void shouldDeleteManufacturerTest() throws Exception {
        //Given
        when(service.getManufacturer(1L)).thenReturn(manufacturerDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/manufacturers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddManufacturerTest() throws Exception {
        //Given
        when(service.createManufacturer(any(ManufacturerDto.class))).thenReturn(manufacturerDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(manufacturerDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/manufacturers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name")));
    }

    @Test
    void shouldUpdateManufacturerTest() throws Exception {
        //Given
        when(service.updateManufacturer(any(ManufacturerDto.class))).thenReturn(manufacturerDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(manufacturerDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/manufacturers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test name")));
    }
}
