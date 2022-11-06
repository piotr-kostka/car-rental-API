package com.kodilla.rental.client;

import com.kodilla.rental.config.WeatherConfig;
import com.kodilla.rental.domain.dto.api.weather.DailyDto;
import com.kodilla.rental.domain.dto.api.weather.WeatherResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherApiClientTestSuite {

    @InjectMocks
    private WeatherApiClient weatherApiClient;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private WeatherConfig weatherConfig;

    @Test
    public void getWeatherForLocationTest() throws URISyntaxException {
        //Given
        when(weatherConfig.getWeatherApiEndpoint()).thenReturn("https://test.com/v1/forecast");

        List<Double> temperature_2m_max = List.of(10.00);
        List<Double> temperature_2m_min = List.of(5.00);
        List<Double> rain_sum = List.of(5.00);
        List<Double> snowfall_sum = List.of(1.00);

        DailyDto dailyDto = new DailyDto(temperature_2m_max, temperature_2m_min, rain_sum, snowfall_sum);
        WeatherResponseDto weatherResponseDto = new WeatherResponseDto(dailyDto);

        URI uri = new URI("https://test.com/v1/forecast?latitude=50.10&longitude=19.00&daily=temperature_2m_max,temperature_2m_min,rain_sum,snowfall_sum&timezone=auto");
        when(restTemplate.getForObject(uri, WeatherResponseDto.class)).thenReturn(weatherResponseDto);

        //When
        WeatherResponseDto expected = weatherApiClient.getWeatherForLocation();

        //Then
        assertEquals(10.0, expected.getDaily().getTemperature_2m_max().get(0));
        assertEquals(5.00, expected.getDaily().getTemperature_2m_min().get(0));
        assertEquals(5.00, expected.getDaily().getRain_sum().get(0));
        assertEquals(1.00, expected.getDaily().getSnowfall_sum().get(0));
    }
}
