package com.bazinga.eg.catalogservice.common.configuration;

import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.application.service.BookService;
import com.bazinga.eg.catalogservice.application.service.impl.BookServiceImpl;
import com.bazinga.eg.catalogservice.common.audit.DataConfiguration;
import com.bazinga.eg.catalogservice.common.dataloader.BookDataLoader;
import com.bazinga.eg.catalogservice.common.property.CatalogProperties;
import com.bazinga.eg.catalogservice.common.property.CatalogTestDataProperties;
import com.bazinga.eg.catalogservice.persistence.mapper.BookPersistableMapper;
import com.bazinga.eg.catalogservice.persistence.repository.BookRepositoryImpl;
import com.bazinga.eg.catalogservice.persistence.repository.jdbc.BookJdbcRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OpenApiConfiguration.class, DataConfiguration.class})
@EnableConfigurationProperties({CatalogTestDataProperties.class})
public class ApplicationConfiguration {

    @Bean
    public BookRepository bookRepository(BookPersistableMapper bookPersistableMapper,
                                         BookJdbcRepository bookJdbcRepository) {
        return new BookRepositoryImpl(bookPersistableMapper, bookJdbcRepository);
    }

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookServiceImpl(bookRepository);
    }

    @Bean
    public CatalogProperties catalogProperties() {
        return new CatalogProperties();
    }

    @Bean
    @ConditionalOnProperty(name = "catalog.test-data.enabled", havingValue = "true")
    public BookDataLoader bookDataLoader(BookRepository bookRepository) {
        return new BookDataLoader(bookRepository);
    }
}
