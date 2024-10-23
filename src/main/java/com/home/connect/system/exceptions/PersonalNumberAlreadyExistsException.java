package com.home.connect.system.exceptions;

public final class PersonalNumberAlreadyExistsException extends RuntimeException {
    public PersonalNumberAlreadyExistsException() {super("O CPF está em uso!");}
}