package com.bazinga.eg.catalogservice;

import com.bazinga.eg.catalogservice.application.domain.model.Book;
import com.bazinga.eg.catalogservice.application.service.BookService;
import com.bazinga.eg.catalogservice.common.exception.ExceptionFactory;
import com.bazinga.eg.catalogservice.common.exception.enums.ExceptionCode;
import com.bazinga.eg.catalogservice.resource.controller.BookController;
import com.bazinga.eg.catalogservice.resource.payload.BookDTO;
import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@TestPropertySource(properties = {
        "ACTIVE_PROFILE=it",
        "SERVER_PORT=8080"
})
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookExistingThenShouldReturnBook() throws Exception {
        String isbn = "1234567890";

        NewBook newBook = new NewBook(isbn, "Title", "Author", 9.99);

        Book book = new Book(newBook);

        given(bookService.viewBookDetails(isbn)).willReturn(new BookDTO(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/catalog/books/" + isbn))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"));
    }


    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "1234567890";

        given(bookService.viewBookDetails(isbn)).willThrow(ExceptionFactory.create(ExceptionCode.NO_DATA_FOUND, new RuntimeException("A book with ISBN " + isbn + " was not found.")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/catalog/books/" + isbn))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value(ExceptionCode.NO_DATA_FOUND.getTitle()))
                .andExpect(jsonPath("$.message").value(ExceptionCode.NO_DATA_FOUND.getMessage()));
    }

}
