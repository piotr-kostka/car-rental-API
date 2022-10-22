package com.kodilla.rental.exception;

public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(Class className, long id) {
        super(className.getSimpleName() + " with given id: " + id + " already exist!");
    }
}
