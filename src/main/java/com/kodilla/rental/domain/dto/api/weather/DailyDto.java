package com.kodilla.rental.domain.dto.api.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyDto {

    @JsonProperty
    private float[] temperature_2m_max;

    @JsonProperty
    private float[] temperature_2m_min;

    @JsonProperty
    private float[] rain_sum;

    @JsonProperty
    private float[] snowfall_sum;
}
