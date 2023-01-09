package com.digicert.coding.exception;

import com.digicert.coding.model.BookError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<BookError> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
        BookError bookError = new BookError(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bookError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BookAlreadyExistsException.class})
    public ResponseEntity<BookError> handleBookAlreadyExistsException(BookAlreadyExistsException bookAlreadyExistsException) {
        BookError bookError = new BookError(bookAlreadyExistsException.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bookError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidISBNException.class})
    public ResponseEntity<BookError> handleInvalidISBNException(InvalidISBNException invalidISBNException) {
        BookError bookError = new BookError(invalidISBNException.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bookError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<BookError> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BookError bookError = new BookError(methodArgumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bookError, HttpStatus.BAD_REQUEST);
    }
}
