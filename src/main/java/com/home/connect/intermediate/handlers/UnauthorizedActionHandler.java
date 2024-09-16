package com.home.connect.intermediate.handlers;

import com.home.connect.core.domain.exceptions.UnauthorizedActionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class UnauthorizedActionHandler {
    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<String> exception(UnauthorizedActionException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Mensagem: " + ex.getMessage());
    }
}