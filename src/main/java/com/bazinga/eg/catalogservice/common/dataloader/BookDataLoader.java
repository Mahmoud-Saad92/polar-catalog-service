package com.bazinga.eg.catalogservice.common.dataloader;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

public record BookDataLoader(BookRepository bookRepository) {

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();

        NewBook book1 = new NewBook("1234567891", "Northern Lights", "Lyra Silvestar", 9.90);
        NewBook book2 = new NewBook("1234567892", "Polar Journey", "Iorek Polarson", 12.90);

//        bookRepository.saveAll(List.of(new Book(book1), new Book(book2)));

        List.of(new Book(book1), new Book(book2)).forEach(bookRepository::save);
    }
}
