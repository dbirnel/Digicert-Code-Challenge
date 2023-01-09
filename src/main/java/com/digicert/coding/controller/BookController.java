package com.digicert.coding.controller;

import com.digicert.coding.model.BookModel;
import com.digicert.coding.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/getBook/{ISBN}")
    public ResponseEntity<BookModel> getBook(@PathVariable String ISBN) {
        return ResponseEntity.ok(bookService.getBook(ISBN));
    }

    @PostMapping("/addBook")
    public ResponseEntity<HttpStatus> addBook(@RequestBody @Valid BookModel bookModel) {
        bookService.addBook(bookModel);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/deleteBook/{ISBN}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String ISBN) {
        bookService.deleteBook(ISBN);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateBook")
    public ResponseEntity<HttpStatus> updateBook(@RequestBody @Valid BookModel bookModel) {
        bookService.updateBook(bookModel);
        return ResponseEntity.ok().build();
    }
}
