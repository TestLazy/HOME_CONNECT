package com.home.connect.core.domain.exceptions;

public final class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {super("A senha está incorreta!");}
}