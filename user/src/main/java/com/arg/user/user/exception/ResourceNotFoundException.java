package com.arg.user.user.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("ResourceNotFoundException");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
