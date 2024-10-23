package com.home.connect.system.exceptions;

public final class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {super("Usuário não encontrado!");}
}