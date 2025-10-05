package com.bazinga.eg.catalogservice.application.service;

import com.bazinga.eg.catalogservice.resource.payload.BookDTO;
import com.bazinga.eg.catalogservice.resource.payload.EditBook;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface BookService {
    Iterable<BookDTO> viewBookList(Pageable pageable);

    BookDTO viewBookDetails(String isbn);

    BookDTO addBookToCatalog(NewBook newBook);

    void deleteBookFromCatalog(String isbn);

    void editBookDetails(String isbn, EditBook editBook) throws MethodArgumentNotValidException;
}
