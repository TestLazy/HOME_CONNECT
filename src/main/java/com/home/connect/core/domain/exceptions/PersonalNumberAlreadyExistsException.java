package com.home.connect.core.domain.exceptions;

public final class PersonalNumberAlreadyExistsException extends RuntimeException {
    public PersonalNumberAlreadyExistsException() {super("O CPF está em uso!");}
}