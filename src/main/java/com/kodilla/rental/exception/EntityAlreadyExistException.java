package com.kodilla.rental.exception;

public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(Class className) {
        super(className.getSimpleName() + " already exist!");
    }
}
