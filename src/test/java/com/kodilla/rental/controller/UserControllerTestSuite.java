package com.kodilla.rental.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.rental.config.LocalDateAdapter;
import com.kodilla.rental.domain.dto.UserDto;
import com.kodilla.rental.service.UserDbService;
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
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDbService service;

    private UserDto userDto;
    private final List<UserDto> userDtoList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        userDto = new UserDto(1L, "name", "lastname", 940930123212L, "address",
                "mail@mail.com", "password", "123456789", BigDecimal.ZERO, LocalDate.of(2022,10,10), null);
        userDtoList.add(userDto);
    }

    @Test
    void shouldGetUsersTest() throws Exception {
        //Given
        when(service.getAllUsers()).thenReturn(userDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].toPay", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].signupDate", Matchers.is("2022-10-10")));
    }

    @Test
    void shouldGetUserTest() throws Exception {
        //Given
        when(service.getUser(1L)).thenReturn(userDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.signupDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toPay", Matchers.is(0)));
    }

    @Test
    void shouldDeleteUserTest() throws Exception {
        //Given
        when(service.getUser(1L)).thenReturn(userDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddUserTest() throws Exception {
        //Given
        UserDto jsonInput = new UserDto(1L, "name", "lastname", 940930123212L, "address",
                "mail@mail.com", "password", "123456789", null, null, null);
        when(service.createUser(any(UserDto.class))).thenReturn(userDto);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(jsonInput);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.signupDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toPay", Matchers.is(0)));
    }

    @Test
    void shouldUpdateUserTest() throws Exception {
        //Given
        when(service.updateUser(any(UserDto.class))).thenReturn(userDto);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is(940930123212L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is("address")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mail", Matchers.is("mail@mail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCardNo", Matchers.is("123456789")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.signupDate", Matchers.is("2022-10-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toPay", Matchers.is(0)));
    }
}
