package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.enums.Currency;
import com.kodilla.rental.domain.enums.RentalStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    private BigDecimal toPay;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "rental_carts",
            joinColumns = {@JoinColumn(name = "rental_id", referencedColumnName = "rental_id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id", referencedColumnName = "car_id")}
    )
    private List<Car> cars;
}
