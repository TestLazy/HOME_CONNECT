package com.home.connect.intermediate.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class DatabaseViolationHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> exception(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Mensagem: Ocorreu algum erro!");
    }
}