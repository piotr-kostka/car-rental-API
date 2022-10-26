package com.kodilla.rental.service.api;

import com.kodilla.rental.client.WeatherApiClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherApiService {

    @Autowired
    private WeatherApiClient weatherApiClient;

    public double getAverageTemperature() {
        double avgTemp = 0;
        double[] maxTemp = weatherApiClient.getWeatherForLocation().get(0).getDaily().get(0).getTemperature_2m_max();
        double[] minTemp = weatherApiClient.getWeatherForLocation().get(0).getDaily().get(0).getTemperature_2m_min();
        double[] mergedArray = ArrayUtils.addAll(maxTemp, minTemp);

        for (int i = 0; i < mergedArray.length; i++) {
            avgTemp += mergedArray[i];
        }
        return avgTemp / mergedArray.length;
    }

    public double getTotalRainfall() {
        double totalRainfall = 0;
        double[] rainfall = weatherApiClient.getWeatherForLocation().get(0).getDaily().get(0).getRain_sum();

        for (int r = 0; r < rainfall.length; r++) {
            totalRainfall += rainfall[r];
        }
        return totalRainfall;
    }

    public double getTotalSnowfall() {
        double totalSnowfall = 0;
        double[] snowfall = weatherApiClient.getWeatherForLocation().get(0).getDaily().get(0).getSnowfall_sum();

        for (int r = 0; r < snowfall.length; r++) {
            totalSnowfall += snowfall[r];
        }
        return totalSnowfall;
    }
}
