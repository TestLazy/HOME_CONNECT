package com.home.connect.system.exceptions;

public final class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {super("Email não registrado!");}
}