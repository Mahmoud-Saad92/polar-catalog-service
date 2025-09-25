package com.bazinga.eg.catalogservice.application.domain.model;

import com.bazinga.eg.catalogservice.resource.payload.EditBook;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;

public record Book(Long bookId,
                   String isbn,
                   String title,
                   String author,
                   Double price) {

    public Book(NewBook newBook) {
        this(null, newBook.isbn(), newBook.title(), newBook.author(), newBook.price());
    }

    public Book(String isbn, EditBook editBook) {
        this(null, isbn, editBook.title(), editBook.author(), editBook.price());
    }

    public Book(Long bookId, String isbn, EditBook editBook) {
        this(bookId, isbn, editBook.title(), editBook.author(), editBook.price());
    }
}
