package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.Model;
import com.kodilla.rental.exception.EntityNotFoundException;

public class ModelNotFoundException extends EntityNotFoundException {

    public ModelNotFoundException(long id) {
        super(Model.class, id);
    }
}
