package com.digicert.coding.model;

import org.springframework.http.HttpStatus;

public class BookError {
    private final String message;
    private final HttpStatus httpStatus;

    public BookError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
