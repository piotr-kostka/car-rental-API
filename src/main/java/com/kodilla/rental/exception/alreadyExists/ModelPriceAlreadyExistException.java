package com.kodilla.rental.exception.alreadyExists;

import com.kodilla.rental.domain.ModelPrice;
import com.kodilla.rental.exception.EntityAlreadyExistException;

public class ModelPriceAlreadyExistException extends EntityAlreadyExistException {

    public ModelPriceAlreadyExistException(long id) {
        super(ModelPrice.class, id);
    }
}
