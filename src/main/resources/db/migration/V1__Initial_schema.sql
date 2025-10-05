CREATE SCHEMA IF NOT EXISTS catalogsvc;

DROP TABLE IF EXISTS catalogsvc.tbl_book;

CREATE TABLE IF NOT EXISTS catalogsvc.tbl_book
(
    id                 BIGSERIAL PRIMARY KEY,
    isbn               VARCHAR(13)  NOT NULL UNIQUE,
    title              VARCHAR(255) NOT NULL,
    author             VARCHAR(255) NOT NULL,
    price              DECIMAL(10, 2) NOT NULL,
    version            BIGINT NOT NULL DEFAULT 0,
    created_by         VARCHAR(225),
    last_modified_by   VARCHAR(225),
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_tbl_book_isbn ON catalogsvc.tbl_book (isbn);