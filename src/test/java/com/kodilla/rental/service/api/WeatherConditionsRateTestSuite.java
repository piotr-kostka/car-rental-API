package com.kodilla.rental.service.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherConditionsRateTestSuite {

    @InjectMocks
    private WeatherConditionsRate weatherConditionsRate;

    @Mock
    private WeatherApiService weatherApiService;

    @Test
    public void getWeatherRateTest() {
        //Given
        when(weatherApiService.getAverageTemperature()).thenReturn(15.00);
        when(weatherApiService.getTotalRainfall()).thenReturn(6.00);
        when(weatherApiService.getTotalSnowfall()).thenReturn(0.00);
        //When
        double expected = weatherConditionsRate.getWeatherRate();

        //Then
        assertEquals(1.3 ,expected);
    }
}
