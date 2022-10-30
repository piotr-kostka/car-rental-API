package com.kodilla.rental.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherConditionsRate {
    private final WeatherApiService weatherApiService;

    public double getWeatherRate() {
        double avgTemperature = weatherApiService.getAverageTemperature();
        double totalFall = weatherApiService.getTotalRainfall() + weatherApiService.getTotalSnowfall();

        if (avgTemperature > 10 && totalFall > 0 && totalFall <= 5) return 1.1;
        else if (avgTemperature > 10 && totalFall > 5) return 1.3;
        else if (avgTemperature <= 0 && totalFall == 0) return 1.5;
        else if (avgTemperature <= 0 && totalFall > 0) return 2.0;
        else if (avgTemperature > 0 && avgTemperature <= 10 && totalFall == 0) return 1.2;
        else if (avgTemperature > 0 && avgTemperature <= 10 && totalFall > 0) return 1.5;
        else if (totalFall > 10) return 2.5;
        else return 1;
    }
}
