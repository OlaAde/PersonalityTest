package com.teamway.personalitytest.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("Resource not found");
    }
}
