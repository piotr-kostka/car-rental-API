package com.kodilla.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    private long pesel;

    @NotNull
    private String address;

    @NotNull
    @Email
    private String mail;

    @NotNull
    private String password;

    @NotNull
    @Column(name = "credit_card_number")
    private String creditCardNo;

    @Column(name = "amount_to_pay")
    private BigDecimal toPay;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!address.equals(user.address)) return false;
        return mail.equals(user.mail);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + mail.hashCode();
        return result;
    }
}
