package com.home.connect.core.domain.exceptions;

public final class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {super("O nome de usuário está em uso!");}
}