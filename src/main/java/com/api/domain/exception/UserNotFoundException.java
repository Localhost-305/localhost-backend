package com.api.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Exception e) {
        super("A lista de clientes não pôde ser encontrada: " + e.getMessage());
    }

}
