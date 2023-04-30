package com.example.ems.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    // Constructor
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
