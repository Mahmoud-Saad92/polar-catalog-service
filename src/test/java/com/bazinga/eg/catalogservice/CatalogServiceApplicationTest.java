package com.bazinga.eg.catalogservice;

import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class CatalogServiceApplicationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBookCreated() {
        NewBook newBook = new NewBook("1234567893", "Title", "Author", 9.99);

        webTestClient
                .post()
                .uri("/api/v1/catalog/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Void.class);
    }
}
