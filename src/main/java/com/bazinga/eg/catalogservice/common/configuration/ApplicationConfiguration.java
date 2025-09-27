package com.bazinga.eg.catalogservice.common.configuration;

import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.application.service.BookService;
import com.bazinga.eg.catalogservice.application.service.impl.BookServiceImpl;
import com.bazinga.eg.catalogservice.persistence.mapper.BookPersistableMapper;
import com.bazinga.eg.catalogservice.persistence.repository.InMemoryBookRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OpenApiConfiguration.class})
public class ApplicationConfiguration {

    @Bean
    public BookRepository bookRepository(BookPersistableMapper bookPersistableMapper) {
        return new InMemoryBookRepositoryImpl(bookPersistableMapper);
    }

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookServiceImpl(bookRepository);
    }
}
