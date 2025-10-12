package com.bazinga.eg.catalogservice;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.common.configuration.ApplicationConfiguration;
import com.bazinga.eg.catalogservice.persistence.mapper.BookPersistableMapper;
import com.bazinga.eg.catalogservice.persistence.mapper.BookPersistableMapperImpl;
import com.bazinga.eg.catalogservice.persistence.repository.model.BookPersistable;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import({ApplicationConfiguration.class, BookPersistableMapperImpl.class})
@ActiveProfiles("it")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryJdbcIntegrationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPersistableMapper bookPersistableMapper;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234567893";

        NewBook newBook = new NewBook(bookIsbn, "Title", "Author", 9.99);

        Book book = new Book(newBook);

        BookPersistable bookPersistable = bookPersistableMapper.toBookPersistable(book);

        jdbcAggregateTemplate.insert(bookPersistable);

        Optional<Book> bookOptional = bookRepository.findByIsbn(bookIsbn);

        assertThat(bookOptional).isPresent();
        assertThat(bookOptional.get().isbn()).isEqualTo(bookIsbn);
    }
}
