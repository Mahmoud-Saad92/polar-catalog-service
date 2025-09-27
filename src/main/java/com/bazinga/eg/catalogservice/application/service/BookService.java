package com.bazinga.eg.catalogservice.application.service;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.resource.payload.EditBook;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface BookService {
    Iterable<Book> viewBookList();

    Book viewBookDetails(String isbn);

    Book addBookToCatalog(Book book);

    void deleteBookFromCatalog(String isbn);

    Book editBookDetails(String isbn, EditBook editBook) throws MethodArgumentNotValidException;
}
