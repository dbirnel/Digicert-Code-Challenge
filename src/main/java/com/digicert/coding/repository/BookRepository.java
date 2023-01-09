package com.digicert.coding.repository;

import com.digicert.coding.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, String> {
}
