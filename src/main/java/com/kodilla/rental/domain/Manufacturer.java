package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private long manufacturerId;

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(
            targetEntity = Model.class,
            mappedBy = "manufacturer",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Model> models;
}
