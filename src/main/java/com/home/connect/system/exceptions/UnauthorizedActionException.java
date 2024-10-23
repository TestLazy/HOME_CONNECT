package com.home.connect.system.exceptions;

public final class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException() {super("Não é possível realizar essa ação!");}
}