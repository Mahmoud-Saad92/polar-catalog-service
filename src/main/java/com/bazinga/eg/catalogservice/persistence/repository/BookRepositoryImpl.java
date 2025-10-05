package com.bazinga.eg.catalogservice.persistence.repository;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.persistence.mapper.BookPersistableMapper;
import com.bazinga.eg.catalogservice.persistence.repository.jdbc.BookJdbcRepository;
import com.bazinga.eg.catalogservice.persistence.repository.model.BookPersistable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Slf4j
public class BookRepositoryImpl implements BookRepository {

    private final BookPersistableMapper bookPersistableMapper;
    private final BookJdbcRepository bookJdbcRepository;

    public BookRepositoryImpl(BookPersistableMapper bookPersistableMapper,
                              BookJdbcRepository bookJdbcRepository) {
        this.bookPersistableMapper = bookPersistableMapper;
        this.bookJdbcRepository = bookJdbcRepository;
    }

    @Override
    public Iterable<Book> findAll(Pageable pageable) {
        PageRequest pageRequest =
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "isbn")));

        Page<BookPersistable> books = bookJdbcRepository.findAll(pageRequest);

        return books.map(bookPersistableMapper::toBook);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookJdbcRepository
                .findByIsbn(isbn)
                .map(bookPersistableMapper::toBook);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookJdbcRepository.existsByIsbn(isbn);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book save(Book book) {
        BookPersistable bookPersistable = bookPersistableMapper.toBookPersistable(book);

        BookPersistable bookPersistableSaved = bookJdbcRepository.save(bookPersistable);

        return bookPersistableMapper.toBook(bookPersistableSaved);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        bookJdbcRepository.deleteByIsbn(isbn);
    }

    @Override
    public void deleteAll() {
        bookJdbcRepository.deleteAll();
    }

    @Override
    public void saveAll(Iterable<Book> books) {
        List<Book> bookList =
                StreamSupport
                        .stream(books.spliterator(), false)
                        .toList();

        bookJdbcRepository.saveAll(bookPersistableMapper.toBookPersistableList(bookList));
    }
}
