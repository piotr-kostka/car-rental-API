package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class ManufacturerAlreadyExistException extends EntityAlreadyExistException {

    public ManufacturerAlreadyExistException() {
        super(Manufacturer.class);
    }
}
