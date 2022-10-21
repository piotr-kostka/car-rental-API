package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private long modelId;

    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private Manufacturer manufacturer;

    @NotNull
    private String name;

    @NotNull
    private float engineSize;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @NotNull
    private int productionYear;

    private String color;
    private int seatsQuantity;
    private int doorQuantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_price")
    private ModelPrice modelPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @JsonIgnore
    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "model",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Car> cars;
}
