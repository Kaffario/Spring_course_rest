package com.example.spring.rest.exeption_handling;

public class NoEmployeeException extends RuntimeException {
    public NoEmployeeException(String message) {
        super(message);
    }
}
