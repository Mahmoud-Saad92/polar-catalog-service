package com.bazinga.eg.catalogservice.application.domain.repository;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll(Pageable pageable);

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    Book save(Book book);

    void deleteByIsbn(String isbn);

    void deleteAll();

    void saveAll(Iterable<Book> books);
}
