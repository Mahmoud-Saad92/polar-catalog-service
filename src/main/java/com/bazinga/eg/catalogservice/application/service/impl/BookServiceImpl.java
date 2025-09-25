package com.bazinga.eg.catalogservice.application.service.impl;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.application.service.BookService;
import com.bazinga.eg.catalogservice.common.exception.ExceptionFactory;
import com.bazinga.eg.catalogservice.common.exception.enums.ExceptionCode;
import com.bazinga.eg.catalogservice.resource.payload.EditBook;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Pattern;

public record BookServiceImpl(BookRepository bookRepository) implements BookService {

    private static final Pattern ISBN_PATTERN = Pattern.compile("^([0-9]{10}|[0-9]{13})$");

    @Override
    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    @Override
    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> ExceptionFactory.create(ExceptionCode.NO_DATA_FOUND, new RuntimeException("A book with ISBN " + isbn + " was not found.")));
    }

    @Override
    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn()))
            throw ExceptionFactory.create(ExceptionCode.DUPLICATE_DATA_FOUND, new RuntimeException("A book with ISBN " + book.isbn() + " already exists."));

        return bookRepository.save(book);
    }

    @Override
    public void deleteBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public Book editBookDetails(final String isbn, final EditBook editBook) throws MethodArgumentNotValidException {
        if (validateIsbnFormat(isbn)) {
            throw new MethodArgumentNotValidException(createMethodParameter(), createTableBindingResult(isbn));
        }

        return bookRepository.findByIsbn(isbn)
                .map(exsitingBook -> {
                    Book bookToUpdate = new Book(exsitingBook.bookId(), exsitingBook.isbn(), editBook);
                    return bookRepository.save(bookToUpdate);
                }).orElseGet(() -> addBookToCatalog(new Book(isbn, editBook)));
    }

    private boolean validateIsbnFormat(String isbn) {
        return Objects.isNull(isbn) || !ISBN_PATTERN.matcher(isbn.trim()).matches();
    }

    private BeanPropertyBindingResult createTableBindingResult(String isbn) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(isbn, "isbn");
        bindingResult.addError(new FieldError("isbn", "isbn", isbn, false, null, null, "The ISBN format must be valid."));
        return bindingResult;
    }

    private MethodParameter createMethodParameter() {
        try {
            Method method = BookService.class.getMethod("editBookDetails", String.class, EditBook.class);
            return new MethodParameter(method, 0); // first parameter (isbn)
        } catch (NoSuchMethodException e) {
            // This should never happen since we know the method exists
            throw new RuntimeException("Unable to create method parameter", e);
        }
    }
}
