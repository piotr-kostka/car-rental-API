package com.kodilla.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.domain.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDto {

    private long carId;
    private Model model;
    private String licenseNumber;
    private BigDecimal price;
    private CarStatus carStatus;

    @JsonIgnore
    private List<Rental> rentals;
}
