package com.home.connect.system.handlers;

import com.home.connect.system.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class EntityNotFounddHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> exception(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Mensagem: " + ex.getMessage());
    }
}