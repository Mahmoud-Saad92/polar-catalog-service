package com.bazinga.eg.catalogservice.persistence.mapper;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.persistence.repository.model.BookPersistable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookPersistableMapper {

    @Mapping(source = "bookId", target = "id")
    BookPersistable toBookPersistable(Book book);

    @Mapping(source = "id", target = "bookId")
    Book toBook(BookPersistable bookPersistable);

    Collection<BookPersistable> toBookPersistableList(Collection<Book> books);

    Collection<Book> toBookList(Collection<BookPersistable> bookPersistableList);
}
