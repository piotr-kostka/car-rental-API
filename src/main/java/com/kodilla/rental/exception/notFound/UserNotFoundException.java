package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.User;
import com.kodilla.rental.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(long id) {
        super(User.class, id);
    }
}
