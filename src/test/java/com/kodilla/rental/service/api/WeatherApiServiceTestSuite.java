package com.kodilla.rental.service.api;

import com.kodilla.rental.client.WeatherApiClient;
import com.kodilla.rental.domain.dto.api.weather.DailyDto;
import com.kodilla.rental.domain.dto.api.weather.WeatherResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherApiServiceTestSuite {

    @InjectMocks
    private WeatherApiService weatherApiService;

    @Mock
    private WeatherApiClient weatherApiClient;

    WeatherResponseDto weatherResponseDto;
    List<Double> temperature_2m_max;
    List<Double> temperature_2m_min;
    List<Double> rain_sum;
    List<Double> snowfall_sum;

    @BeforeEach
    void prepareData() {
        temperature_2m_max = List.of(10.00);
        temperature_2m_min = List.of(5.00);
        rain_sum = List.of(5.00);
        snowfall_sum = List.of(1.00);
        DailyDto dailyDto = new DailyDto(temperature_2m_max, temperature_2m_min, rain_sum, snowfall_sum);
        weatherResponseDto = new WeatherResponseDto(dailyDto);
    }

    @Test
    public void getAverageTemperatureTest() {
        //Given
        when(weatherApiClient.getWeatherForLocation()).thenReturn(weatherResponseDto);

        //When
        double expected = weatherApiService.getAverageTemperature();

        //Then
        assertEquals(7.5, expected, 0.01);
    }

    @Test
    public void getTotalRainfallTest() {
        //Given
        when(weatherApiClient.getWeatherForLocation()).thenReturn(weatherResponseDto);

        //When
        double expected = weatherApiService.getTotalRainfall();

        //Then
        assertEquals(5.0, expected);
    }

    @Test
    public void getTotalSnowfallTest() {
        //Given
        when(weatherApiClient.getWeatherForLocation()).thenReturn(weatherResponseDto);

        //When
        double expected = weatherApiService.getTotalSnowfall();

        //Then
        assertEquals(1.0, expected);
    }
}
