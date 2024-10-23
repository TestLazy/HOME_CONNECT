package com.home.connect.system.exceptions;

public final class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {super("A senha está incorreta!");}
}