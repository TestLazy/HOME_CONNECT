package com.home.connect.core.domain.exceptions;

public final class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {super("Email não registrado!");}
}