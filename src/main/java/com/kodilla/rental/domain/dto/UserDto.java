package com.kodilla.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private long userId;
    private String firstName;
    private String lastName;
    private long pesel;
    private String address;
    private String mail;
    private String password;
    private String creditCardNo;
    private double toPay;
    private boolean isBlocked;
    private LocalDate signupDate;

    @JsonIgnore
    private Set<Rental> rentals;
}
