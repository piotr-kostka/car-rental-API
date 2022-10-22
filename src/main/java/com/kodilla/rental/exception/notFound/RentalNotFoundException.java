package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.exception.EntityNotFoundException;

public class RentalNotFoundException extends EntityNotFoundException {

    public RentalNotFoundException(long id) {
        super(Rental.class, id);
    }
}
