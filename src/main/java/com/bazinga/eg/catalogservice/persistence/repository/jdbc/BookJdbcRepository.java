package com.bazinga.eg.catalogservice.persistence.repository.jdbc;

import com.bazinga.eg.catalogservice.persistence.repository.model.BookPersistable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookJdbcRepository extends CrudRepository<BookPersistable, Long>, PagingAndSortingRepository<BookPersistable, Long> {
    Optional<BookPersistable> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    @Modifying
    @Transactional
    @Query("""
            delete from catalogsvc.TBL_BOOK tb
            where tb.isbn = :isbn
            """)
    void deleteByIsbn(@Param("isbn") String isbn);

    @Modifying
    @Transactional
    @Query("""
            delete from catalogsvc.TBL_BOOK
            """)
    void deleteAll();
}
