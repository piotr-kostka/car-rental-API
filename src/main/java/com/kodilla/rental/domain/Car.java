package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.enums.CarStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @JsonIgnore
    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "car",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Rental> rentals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!model.equals(car.model)) return false;
        return licenseNumber.equals(car.licenseNumber);
    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + licenseNumber.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return model + ", license number: " + licenseNumber;
    }
}
