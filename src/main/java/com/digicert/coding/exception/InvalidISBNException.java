package com.digicert.coding.exception;

public class InvalidISBNException extends RuntimeException {
    public InvalidISBNException(String message) {
        super(message);
    }

    public InvalidISBNException(String message, Throwable cause) {
        super(message, cause);
    }
}
