package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.Model;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class ModelAlreadyExistException extends EntityAlreadyExistException {

    public ModelAlreadyExistException(long id) {
        super(Model.class, id);
    }
}
