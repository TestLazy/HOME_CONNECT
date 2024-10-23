package com.home.connect.system.handlers;

import com.home.connect.system.exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class InvalidPasswordHandler {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> exception(InvalidPasswordException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Mensagem: " + ex.getMessage());
    }
}