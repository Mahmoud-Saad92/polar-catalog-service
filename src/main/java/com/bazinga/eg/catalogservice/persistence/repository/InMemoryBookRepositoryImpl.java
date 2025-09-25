package com.bazinga.eg.catalogservice.persistence.repository;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.persistence.mapper.BookPersistableMapper;
import com.bazinga.eg.catalogservice.persistence.repository.model.BookPersistable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class InMemoryBookRepositoryImpl implements BookRepository {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);
    private static final Map<String, BookPersistable> BOOKS_MAP = new ConcurrentHashMap<>();
    private final BookPersistableMapper bookPersistableMapper;

    @Autowired
    public InMemoryBookRepositoryImpl(BookPersistableMapper bookPersistableMapper) {
        this.bookPersistableMapper = bookPersistableMapper;
    }

    @Override
    public Iterable<Book> findAll() {
        return BOOKS_MAP.values()
                .stream()
                .map(bookPersistableMapper::toBook)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        BookPersistable bookPersistable = BOOKS_MAP.get(isbn);
        return Objects.nonNull(bookPersistable) ? Optional.of(bookPersistableMapper.toBook(bookPersistable)) : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return Objects.nonNull(BOOKS_MAP.get(isbn));
    }

    @Override
    public Book save(Book book) {
        BookPersistable bookPersistable = bookPersistableMapper.toBookPersistable(book);

        if (Objects.isNull(bookPersistable.getId())) {
            bookPersistable.setId(ID_GENERATOR.getAndIncrement());
        }

        BOOKS_MAP.put(book.isbn(), bookPersistable);

        return bookPersistableMapper.toBook(bookPersistable);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        BOOKS_MAP.remove(isbn);
    }
}
