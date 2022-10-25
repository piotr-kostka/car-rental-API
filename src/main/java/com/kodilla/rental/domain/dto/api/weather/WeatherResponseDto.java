package com.kodilla.rental.domain.dto.api.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDto {

    @JsonProperty
    private float latitude;

    @JsonProperty
    private float longitude;

    @JsonProperty
    private List<DailyDto> daily;
}
