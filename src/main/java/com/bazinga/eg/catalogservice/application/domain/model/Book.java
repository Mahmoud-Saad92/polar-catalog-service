package com.bazinga.eg.catalogservice.application.domain.model;

import com.bazinga.eg.catalogservice.resource.payload.EditBook;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;

import java.time.LocalDateTime;

public record Book(Long bookId,
                   String isbn,
                   String title,
                   String author,
                   Double price,
                   Long version,
                   String createdBy,
                   String lastModifiedBy,
                   LocalDateTime createdDate,
                   LocalDateTime lastModifiedDate) {

    public Book(NewBook newBook) {
        this(null, newBook.isbn(), newBook.title(), newBook.author(), newBook.price(), null, null, null, null, null);
    }

    public Book(Long bookId,
                String isbn,
                Long version,
                String createdBy,
                String lastModifiedBy,
                LocalDateTime createdDate,
                LocalDateTime lastModifiedDate,
                EditBook editBook) {
        this(bookId, isbn, editBook.title(), editBook.author(), editBook.price(), version, createdBy, lastModifiedBy, createdDate, lastModifiedDate);
    }
}
