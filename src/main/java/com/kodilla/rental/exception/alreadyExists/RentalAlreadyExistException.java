package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class RentalAlreadyExistException extends EntityAlreadyExistException {

    public RentalAlreadyExistException(long id) {
        super(Rental.class, id);
    }
}
