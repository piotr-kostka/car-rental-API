package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.UserAddress;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class UserAddressAlreadyExistException extends EntityAlreadyExistException {

    public UserAddressAlreadyExistException(long id) {
        super(UserAddress.class, id);
    }
}
