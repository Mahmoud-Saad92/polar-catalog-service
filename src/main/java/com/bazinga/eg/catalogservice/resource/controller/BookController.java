package com.bazinga.eg.catalogservice.resource.controller;

import com.bazinga.eg.catalogservice.application.service.BookService;
import com.bazinga.eg.catalogservice.resource.payload.BookDTO;
import com.bazinga.eg.catalogservice.resource.payload.EditBook;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Iterable<BookDTO>> get(Pageable pageable) {
        return ResponseEntity.ok(bookService().viewBookList(pageable));
    }

    @GetMapping("{isbn}")
    public ResponseEntity<BookDTO> getByIsbn(@PathVariable String isbn) {
        log.info("Request to retrieve book information with isbn [{}]", isbn);

        return ResponseEntity.ok(bookService().viewBookDetails(isbn));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NewBook newBook, UriComponentsBuilder ucb) {
        log.info("Request for adding a new book to catalog: {}", newBook);

        BookDTO addedBookToCatalog = bookService.addBookToCatalog(newBook);

        URI locationOfNewCashCard =
                ucb
                        .path("/api/v1/books/{isbn}")
                        .buildAndExpand(addedBookToCatalog.isbn())
                        .toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PutMapping("{isbn}")
    public ResponseEntity<BookDTO> update(@PathVariable String isbn, @Valid @RequestBody EditBook editBook) throws MethodArgumentNotValidException {
        log.info("Request to update exist book with isbn [{}]", isbn);

        bookService.editBookDetails(isbn, editBook);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> delete(@PathVariable String isbn) {
        log.info("Request to delete book with isbn [{}]", isbn);

        bookService.deleteBookFromCatalog(isbn);

        return ResponseEntity.noContent().build();
    }
}
