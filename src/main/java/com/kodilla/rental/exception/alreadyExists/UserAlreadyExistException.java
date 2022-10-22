package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.User;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class UserAlreadyExistException extends EntityAlreadyExistException {

    public UserAlreadyExistException(long id) {
        super(User.class, id);
    }
}
