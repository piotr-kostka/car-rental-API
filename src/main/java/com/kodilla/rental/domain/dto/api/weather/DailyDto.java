package com.kodilla.rental.domain.dto.api.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyDto {

    @JsonProperty("temperature_2m_max")
    private List<Double> temperature_2m_max;

    @JsonProperty("temperature_2m_min")
    private List<Double> temperature_2m_min;

    @JsonProperty("rain_sum")
    private List<Double> rain_sum;

    @JsonProperty("snowfall_sum")
    private List<Double> snowfall_sum;
}
