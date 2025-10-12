package com.bazinga.eg.catalogservice.persistence.repository;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.persistence.mapper.BookPersistableMapper;
import com.bazinga.eg.catalogservice.persistence.repository.model.BookPersistable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
    public Iterable<Book> findAll(Pageable pageable) {
        List<Book> allBooks = BOOKS_MAP.values()
                .stream()
                .map(bookPersistableMapper::toBook)
                .sorted((b1, b2) -> {
                    if (pageable.getSort().isSorted()) {
                        return pageable.getSort().stream()
                                .map(order -> compareByOrder(b1, b2, order))
                                .filter(result -> result != 0)
                                .findFirst()
                                .orElse(0);
                    }
                    return 0;
                })
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), allBooks.size());

        if (start >= allBooks.size()) {
            return List.of();
        }

        return allBooks.subList(start, end);
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

    @Override
    public void deleteAll() {
        BOOKS_MAP.clear();
    }

    @Override
    public void saveAll(Iterable<Book> books) {
        books.forEach(this::save);
    }

    private int compareByOrder(Book b1, Book b2, Sort.Order order) {
        int comparison = switch (order.getProperty()) {
            case "isbn" -> b1.isbn().compareTo(b2.isbn());
            case "title" -> b1.title().compareTo(b2.title());
            case "author" -> b1.author().compareTo(b2.author());
            case "price" -> b1.price().compareTo(b2.price());
            default -> 0;
        };
        return order.isAscending() ? comparison : -comparison;
    }
}
