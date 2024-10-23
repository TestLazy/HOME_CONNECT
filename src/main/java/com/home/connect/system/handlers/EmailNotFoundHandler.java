package com.home.connect.system.handlers;

import com.home.connect.system.exceptions.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class EmailNotFoundHandler {
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String> exception(EmailNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Mensagem: " + ex.getMessage());
    }
}