package com.bazinga.eg.catalogservice.resource.controller;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.service.BookService;
import com.bazinga.eg.catalogservice.resource.payload.EditBook;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/catalog/books")
public record BookController(BookService bookService) {

    @GetMapping
    public ResponseEntity<Iterable<Book>> get() {
        return ResponseEntity.ok(bookService().viewBookList());
    }

    @GetMapping("{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable String isbn) {
        log.info("Request to retrieve book information with isbn [{}]", isbn);

        return ResponseEntity.ok(bookService().viewBookDetails(isbn));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NewBook newBook, UriComponentsBuilder ucb) {
        log.info("Request for adding a new book to catalog: {}", newBook);

        Book addedBookToCatalog = bookService.addBookToCatalog(new Book(newBook));

        URI locationOfNewCashCard =
                ucb
                        .path("/api/v1/books/{isbn}")
                        .buildAndExpand(addedBookToCatalog.isbn())
                        .toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Book> update(@PathVariable String isbn, @Valid @RequestBody EditBook editBook) throws MethodArgumentNotValidException {
        log.info("Request to update exist book with isbn [{}]", isbn);

        bookService.editBookDetails(isbn, editBook);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Book> delete(@PathVariable String isbn) {
        log.info("Request to delete book with isbn [{}]", isbn);

        bookService.deleteBookFromCatalog(isbn);

        return ResponseEntity.noContent().build();
    }
}
