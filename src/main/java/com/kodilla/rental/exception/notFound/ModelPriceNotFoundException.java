package com.kodilla.rental.exception.notFound;

import com.kodilla.rental.domain.ModelPrice;
import com.kodilla.rental.exception.EntityNotFoundException;

public class ModelPriceNotFoundException extends EntityNotFoundException {

    public ModelPriceNotFoundException(long id) {
        super(ModelPrice.class, id);
    }
}
