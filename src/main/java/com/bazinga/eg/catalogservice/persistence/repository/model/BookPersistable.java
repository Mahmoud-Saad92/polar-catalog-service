package com.bazinga.eg.catalogservice.persistence.repository.model;

import com.bazinga.eg.catalogservice.common.util.AuditingBaseEntity;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookPersistable extends AuditingBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 9161453794L;

    private Long id;

    private String isbn;

    private String title;

    private String author;

    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPersistable bookPersistable = (BookPersistable) o;
        return Objects.equals(id, bookPersistable.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
