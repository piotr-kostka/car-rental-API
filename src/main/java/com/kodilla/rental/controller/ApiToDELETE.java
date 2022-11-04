package com.kodilla.rental.controller;

import com.kodilla.rental.client.NbpApiClient;
import com.kodilla.rental.client.WeatherApiClient;
import com.kodilla.rental.domain.dto.api.weather.WeatherResponseDto;
import com.kodilla.rental.domain.enums.Currency;
import com.kodilla.rental.service.api.WeatherApiService;
import com.kodilla.rental.service.api.WeatherConditionsRate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiToDELETE {

    private final WeatherApiService apiClient;
    private final NbpApiClient nbpApiClient;

    private final WeatherApiClient weatherApiClient;
    private final WeatherConditionsRate weatherConditionsRate;

    @GetMapping("temperature")
    public void getTemp() {

        double rate = apiClient.getAverageTemperature();
        System.out.println(rate);
    }

    @GetMapping("rain")
    public void getRain() {

        double rate = apiClient.getTotalRainfall();
        System.out.println(rate);
    }

    @GetMapping("snow")
    public void getSnow() {

        double rate = apiClient.getTotalSnowfall();
        System.out.println(rate);
    }

    @GetMapping("rate")
    public Double getRate() {
        return weatherConditionsRate.getWeatherRate();
    }

    @GetMapping("response")
    public WeatherResponseDto getApiResponse() {
        return weatherApiClient.getWeatherForLocation();
    }

    @GetMapping("price")
    public void getCurrency() {

        double rate = nbpApiClient.getExchangeRate(Currency.EUR);
        System.out.println(rate);
    }
}
