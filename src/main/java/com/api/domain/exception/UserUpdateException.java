package com.api.domain.exception;

public class UserUpdateException extends RuntimeException {

    public UserUpdateException(Exception e) {
        super("Ocorreu um erro ao atualizar o usuário: " + e.getMessage());
    }
}