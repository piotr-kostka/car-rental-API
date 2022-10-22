package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.UserAddress;
import com.kodilla.rental.exception.EntityNotFoundException;

public class UserAddressNotFoundException extends EntityNotFoundException {

    public UserAddressNotFoundException(long id) {
        super(UserAddress.class, id);
    }
}
