package com.bazinga.eg.catalogservice.application.domain.repository;

import com.bazinga.eg.catalogservice.application.domain.model.Book;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    Book save(Book book);

    void deleteByIsbn(String isbn);
}
