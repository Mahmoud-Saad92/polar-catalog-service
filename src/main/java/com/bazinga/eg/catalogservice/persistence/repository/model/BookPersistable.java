package com.bazinga.eg.catalogservice.persistence.repository.model;

import com.bazinga.eg.catalogservice.common.util.AuditingBaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_book", schema = "catalogsvc")
public class BookPersistable extends AuditingBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 9161453794L;

    @Id
    private Long id;

    private String isbn;

    private String title;

    private String author;

    private Double price;

    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPersistable bookPersistable = (BookPersistable) o;
        return isbn != null && Objects.equals(isbn, bookPersistable.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
