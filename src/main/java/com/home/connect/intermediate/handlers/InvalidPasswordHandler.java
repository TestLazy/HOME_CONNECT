package com.home.connect.intermediate.handlers;

import com.home.connect.core.domain.exceptions.InvalidPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class InvalidPasswordHandler {
    @ExceptionHandler(InvalidPermissionException.class)
    public ResponseEntity<String> exception(InvalidPermissionException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Mensagem: " + ex.getMessage());
    }
}