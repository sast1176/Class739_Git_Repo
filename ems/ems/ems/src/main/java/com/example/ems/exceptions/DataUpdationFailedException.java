package com.example.ems.exceptions;

public class DataUpdationFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Constructor
    public DataUpdationFailedException(String msg) {
        super(msg);
    }
}
