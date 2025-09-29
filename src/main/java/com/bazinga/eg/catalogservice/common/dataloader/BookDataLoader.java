package com.bazinga.eg.catalogservice.common.dataloader;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.domain.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public record BookDataLoader(BookRepository bookRepository) {

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        Book book1 = new Book(1L, "1234567891", "Northern Lights", "Lyra Silvestar", 9.90);
        Book book2 = new Book(2L, "1234567892", "Polar Journey", "Iorek Polarson", 12.90);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
