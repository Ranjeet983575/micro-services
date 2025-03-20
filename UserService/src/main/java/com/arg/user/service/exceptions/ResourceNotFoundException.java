package com.arg.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource Not Found Exception");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
