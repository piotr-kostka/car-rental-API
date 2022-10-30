package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.enums.Currency;
import com.kodilla.rental.domain.enums.RentalStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private long rentalId;

    private LocalDate rentDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private double priceRate;
    private BigDecimal totalValue;
    private BigDecimal leftToPay;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;
}
