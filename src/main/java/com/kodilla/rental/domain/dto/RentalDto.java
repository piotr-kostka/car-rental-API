package com.kodilla.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.User;
import com.kodilla.rental.domain.enums.Currency;
import com.kodilla.rental.domain.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalDto {

    private long rentalId;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private Currency currency;
    private Double priceRate;
    private BigDecimal totalValue;
    private BigDecimal leftToPay;
    private RentalStatus rentalStatus;
    private LocalDate paymentDate;
    private User user;
    private Car car;
}
