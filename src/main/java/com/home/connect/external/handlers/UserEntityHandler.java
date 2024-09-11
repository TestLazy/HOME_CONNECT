package com.home.connect.external.handlers;

import com.home.connect.core.notifications.UserEntityNotification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class UserEntityHandler {
    @ExceptionHandler(UserEntityNotification.class)
    public ResponseEntity<String> notification(UserEntityNotification ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Mensagem de erro: " + ex.getMessage());
    }
}