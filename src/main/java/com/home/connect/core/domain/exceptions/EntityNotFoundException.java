package com.home.connect.core.domain.exceptions;

public final class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {super("Usuário não encontrado!");}
}