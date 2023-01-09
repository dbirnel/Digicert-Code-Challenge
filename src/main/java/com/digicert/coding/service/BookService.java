package com.digicert.coding.service;

import com.digicert.coding.model.BookModel;

import java.util.List;

public interface BookService {

    List<BookModel> getAllBooks();
    BookModel getBook(String ISBN);
    void addBook(BookModel book);
    void deleteBook(String ISBN);
    void updateBook(BookModel book);
}
