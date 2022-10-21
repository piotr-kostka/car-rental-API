package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String mail;

    @NotNull
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private UserAddress address;

    @NotNull
    @Column(name = "creditCard_number")
    private String creditCardNo;

    @Column(name = "amount_to_pay")
    private String toPay;

    @Column(name = "account_status")
    private boolean isBlocked;

    @NotNull
    @Column(name = "signup_date")
    private LocalDate signupDate;

    @JsonIgnore
    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Rental> rentals;
}
