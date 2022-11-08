package com.kodilla.rental.controller;

import com.google.gson.Gson;
import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.dto.CarDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import com.kodilla.rental.service.CarDbService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
public class CarControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarDbService service;

    private CarDto carDto;
    private final List<CarDto> carDtoList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        Manufacturer manufacturer = new Manufacturer(1L, "test name", null);
        Model model = new Model(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, null);
        carDto = new CarDto(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, null);
        carDtoList.add(carDto);
    }

    @Test
    void shouldGetCarsTest() throws Exception {
        //Given
        when(service.getAllCars()).thenReturn(carDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldGetCarTest() throws Exception {
        //Given
        when(service.getCar(1L)).thenReturn(carDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carStatus", Matchers.is("AVAILABLE")));

    }

    @Test
    void shouldGetCarsByManufacturerTest() throws Exception {
        //Given
        when(service.getCarsByManufacturer(1L)).thenReturn(carDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/manufacturer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldGetCarsByModelTest() throws Exception {
        //Given
        when(service.getCarsByModel(1L)).thenReturn(carDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/model/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldGetCarsByPriceLowerThanTest() throws Exception {
        //Given
        when(service.getCarsByPriceLowerThan(BigDecimal.valueOf(200))).thenReturn(carDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/price/200")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldGetAvailableCarsTest() throws Exception {
        //Given
        when(service.getAvailableCars()).thenReturn(carDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/available")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldDeleteCarTest() throws Exception {
        //Given
        when(service.getCar(1L)).thenReturn(carDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/cars/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddCarTest() throws Exception {
        //Given
        when(service.createCar(any(CarDto.class))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldUpdateCarTest() throws Exception {
        //Given
        when(service.updateCar(any(CarDto.class))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carStatus", Matchers.is("AVAILABLE")));
    }
}
