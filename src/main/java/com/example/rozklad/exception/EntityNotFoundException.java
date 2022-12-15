package com.example.rozklad.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity with this id does not exist")
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Object entity, long id) {
        super("Entity type of: " + entity.getClass().getName() + " with id: " + id + " not found");
    }
}
