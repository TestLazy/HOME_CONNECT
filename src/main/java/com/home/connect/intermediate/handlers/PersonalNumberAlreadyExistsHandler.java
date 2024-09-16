package com.home.connect.intermediate.handlers;

import com.home.connect.core.domain.exceptions.PersonalNumberAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class PersonalNumberAlreadyExistsHandler {
    @ExceptionHandler(PersonalNumberAlreadyExistsException.class)
    public ResponseEntity<String> exception(PersonalNumberAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Mensagem: " + ex.getMessage());
    }
}