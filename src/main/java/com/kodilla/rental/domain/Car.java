package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.enums.CarStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private long carId;

    @ManyToOne
    @JoinColumn(name = "model")
    private Model model;

    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "cars"
    )
    private List<Rental> rentals;
}
