package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.Address;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class AddressAlreadyExistException extends EntityAlreadyExistException {

    public AddressAlreadyExistException(long id) {
        super(Address.class, id);
    }
}
