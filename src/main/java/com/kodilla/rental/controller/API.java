package com.kodilla.rental.controller;

import com.kodilla.rental.client.NbpApiClient;
import com.kodilla.rental.domain.enums.Currency;
import com.kodilla.rental.service.api.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class API {

    private final WeatherApiService apiClient;
    private final NbpApiClient nbpApiClient;

    @GetMapping("weather")
    public void getRate() {

        double rate = apiClient.getAverageTemperature();
        System.out.println(rate);
    }

    @GetMapping("price")
    public void getCurrency() {

        double rate = nbpApiClient.getExchangeRate(Currency.EUR);
        System.out.println(rate);
    }
}
