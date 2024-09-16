package com.home.connect.core.domain.exceptions;

public final class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException() {super("Não é possível realizar essa ação!");}
}