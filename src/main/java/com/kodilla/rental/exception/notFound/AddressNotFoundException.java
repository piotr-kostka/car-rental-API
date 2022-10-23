package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.Address;
import com.kodilla.rental.exception.EntityNotFoundException;

public class AddressNotFoundException extends EntityNotFoundException {

    public AddressNotFoundException(long id) {
        super(Address.class, id);
    }
}
