# `Bazinga (BZA)` catalog-service
This application is part of the Polar Bookshop system and provides the functionality for managing the books in the bookshop catalog.

## REST API

| Endpoint	      | Method   | Req. body  | Status | Resp. body     | Description    		   	     |
|:---------------:|:--------:|:----------:|:------:|:--------------:|:-------------------------------|
| `/books`        | `GET`    |            | 200    | Book[]         | Get all the books in the catalog. |
| `/books`        | `POST`   | Book       | 201    | Book           | Add a new book to the catalog. |
|                 |          |            | 422    |                | A book with the same ISBN already exists. |
| `/books/{isbn}` | `GET`    |            | 200    | Book           | Get the book with the given ISBN. |
|                 |          |            | 404    |                | No book with the given ISBN exists. |
| `/books/{isbn}` | `PUT`    | Book       | 200    | Book           | Update the book with the given ISBN. |
|                 |          |            | 200    | Book           | Create a book with the given ISBN. |
| `/books/{isbn}` | `DELETE` |            | 204    |                | Delete the book with the given ISBN. |

After building the application, you can also run it from the Java CLI:

```bash
$ java -jar target/catalog-service-0.0.1.jar
```

Run to build docker container locally

```bash
$ DOCKER_BUILDKIT=1 docker compose up -d
```


