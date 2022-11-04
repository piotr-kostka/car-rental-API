package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.Car;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class CarAlreadyExistException extends EntityAlreadyExistException {

    public CarAlreadyExistException() {
        super(Car.class);
    }
}
