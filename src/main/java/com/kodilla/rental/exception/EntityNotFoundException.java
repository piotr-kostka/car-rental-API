package com.kodilla.rental.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class className, long id) {
        super(className.getSimpleName() + " with given id: " + id + " doesn't exist!");
    }
}
