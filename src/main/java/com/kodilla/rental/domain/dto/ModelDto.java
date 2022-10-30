package com.kodilla.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelDto {

    private long modelId;
    private Manufacturer manufacturer;
    private String name;
    private double engineSize;
    private BodyType bodyType;
    private int productionYear;
    private String color;
    private int seatsQuantity;
    private int doorQuantity;
    private FuelType fuelType;
    private TransmissionType transmissionType;

    @JsonIgnore
    private Set<Car> cars;
}
