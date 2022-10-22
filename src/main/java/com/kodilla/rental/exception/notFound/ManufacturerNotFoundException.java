package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.exception.EntityNotFoundException;

public class ManufacturerNotFoundException extends EntityNotFoundException {

    public ManufacturerNotFoundException(long id) {
        super(Manufacturer.class, id);
    }
}
