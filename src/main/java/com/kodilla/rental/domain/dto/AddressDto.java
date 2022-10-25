package com.kodilla.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {

    private long addressId;
    private String city;
    private String postalNumber;
    private String street;
    private String houseNumber;
    private int apartmentNumber;
}