package com.bazinga.eg.catalogservice.resource.payload;

import com.bazinga.eg.catalogservice.application.domain.model.Book;

public record BookDTO(String isbn,
                      String title,
                      String author,
                      Double price) {

    public BookDTO(Book book) {
        this(book.isbn(), book.title(), book.author(), book.price());
    }
}
