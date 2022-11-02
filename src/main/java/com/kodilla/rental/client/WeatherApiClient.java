package com.kodilla.rental.client;

import com.kodilla.rental.config.WeatherConfig;
import com.kodilla.rental.domain.dto.api.weather.WeatherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    private final WeatherConfig weatherConfig;

    private final RestTemplate restTemplate;

    public WeatherResponseDto getWeatherForLocation() {
        URI uri = UriComponentsBuilder.fromHttpUrl(weatherConfig.getWeatherApiEndpoint())
                .queryParam("latitude", "50.10")
                .queryParam("longitude", "19.00")
                .queryParam("daily","temperature_2m_max,temperature_2m_min,rain_sum,snowfall_sum")
                .queryParam("timezone", "auto")
                .build()
                .encode().toUri();

        return restTemplate.getForObject(uri, WeatherResponseDto.class);
    }
}
