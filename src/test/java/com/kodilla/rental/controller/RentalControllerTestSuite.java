package com.kodilla.rental.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.rental.config.LocalDateAdapter;
import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.User;
import com.kodilla.rental.domain.dto.RentalDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.domain.enums.Currency;
import com.kodilla.rental.domain.enums.RentalStatus;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import com.kodilla.rental.service.RentalDbService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(RentalController.class)
public class RentalControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalDbService service;

    private RentalDto rentalDto;
    private final List<RentalDto> rentalDtoList = new ArrayList<>();
    private User user;
    private Car car;

    @BeforeEach
    void prepareData() {
        Manufacturer manufacturer = new Manufacturer(1L, "test name", null);
        Model model = new Model(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, null);
        car = new Car(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, null);
        user = new User(1L, "name", "lastname", 940930123212L, "address",
                "mail@mail.com", "password", "123456789", BigDecimal.ZERO, LocalDate.of(2022,10,10), null);
        rentalDto = new RentalDto(1L, LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 11), Currency.EUR,
                1.5, BigDecimal.valueOf(150), BigDecimal.valueOf(150), RentalStatus.RENTED, LocalDate.of(2022, 10, 11), user, car);
        rentalDtoList.add(rentalDto);
    }
    @Test
    void shouldGetRentalsTest() throws Exception {
        //Given
        when(service.getRentals()).thenReturn(rentalDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentalId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].returnDate", Matchers.is("2022-10-11")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency", Matchers.is("EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceRate", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalValue", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].leftToPay", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentalStatus", Matchers.is("RENTED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].returnDate", Matchers.is("2022-10-11")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.toPay", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.signupDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldGetRentalTest() throws Exception {
        //Given
        when(service.getRental(1L)).thenReturn(rentalDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnDate", Matchers.is("2022-10-11")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.is("EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceRate", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.leftToPay", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalStatus", Matchers.is("RENTED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnDate", Matchers.is("2022-10-11")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.toPay", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.signupDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldGetRentalsByUserTest() throws Exception {
        //Given
        when(service.getUserRentals(1L)).thenReturn(rentalDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentals/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentalId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].returnDate", Matchers.is("2022-10-11")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency", Matchers.is("EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceRate", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalValue", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].leftToPay", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentalStatus", Matchers.is("RENTED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].returnDate", Matchers.is("2022-10-11")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.toPay", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.signupDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].car.carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldCreateRentalTest() throws Exception {
        //Given
        RentalDto jsonInput = new RentalDto(1L, null, null, Currency.EUR, null, null, null, null, null, user, car);
        when(service.createRental(any(RentalDto.class))).thenReturn(rentalDto);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(jsonInput);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.is("EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceRate", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.leftToPay", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalStatus", Matchers.is("RENTED")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.toPay", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.signupDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.manufacturer.manufacturerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.manufacturer.name", Matchers.is("test name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.engineSize", Matchers.is(1800.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.bodyType", Matchers.is("SEDAN")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.productionYear", Matchers.is(2012)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.color", Matchers.is("red")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.seatsQuantity", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.doorQuantity", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.fuelType", Matchers.is("DIESEL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.model.transmissionType", Matchers.is("AUTOMATIC")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.licenseNumber", Matchers.is("ST11111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.price", Matchers.is(150)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.car.carStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldReturnCarTest() throws Exception {
        //Given
        when(service.getRental(1L)).thenReturn(rentalDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/rentals/return/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldMakePaymentTest() throws Exception {
        //Given
        when(service.getRental(1L)).thenReturn(rentalDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/rentals/pay/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}