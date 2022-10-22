package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.Car;
import com.kodilla.rental.exception.EntityNotFoundException;

public class CarNotFoundException extends EntityNotFoundException {

    public CarNotFoundException(long id) {
        super(Car.class, id);
    }
}
