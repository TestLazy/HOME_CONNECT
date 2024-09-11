package com.home.connect.external.handlers;

import com.home.connect.core.notifications.PermissionNotification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class PermissionHandler {
    @ExceptionHandler(PermissionNotification.class)
    public ResponseEntity<String> notification(PermissionNotification ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Mensagem de erro: " + ex.getMessage());
    }
}