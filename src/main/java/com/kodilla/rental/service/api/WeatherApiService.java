package com.kodilla.rental.service.api;

import com.kodilla.rental.client.WeatherApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class WeatherApiService {

    private final WeatherApiClient weatherApiClient;

    public double getAverageTemperature() throws ArithmeticException{
        List<Double> maxTemp = weatherApiClient.getWeatherForLocation().getDaily().getTemperature_2m_max();
        List<Double> minTemp = weatherApiClient.getWeatherForLocation().getDaily().getTemperature_2m_min();
        List<Double> mergedList = Stream.of(maxTemp, minTemp)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        double sum = 0;

        if (mergedList.size() == 0) {
            throw new ArithmeticException();
        }

        for (Double temperature : mergedList) {
            sum += temperature;
        }

        return sum / mergedList.size();
    }

    public double getTotalRainfall() {
        double totalRainfall = 0;
        List<Double> rainfall = weatherApiClient.getWeatherForLocation().getDaily().getRain_sum();

        for (Double rain : rainfall) {
            totalRainfall += rain;
        }
        return totalRainfall;
    }

    public double getTotalSnowfall() {
        double totalSnowfall = 0;
        List<Double> snowfall = weatherApiClient.getWeatherForLocation().getDaily().getSnowfall_sum();

        for (Double snow : snowfall) {
            totalSnowfall += snow;
        }
        return totalSnowfall;
    }
}
