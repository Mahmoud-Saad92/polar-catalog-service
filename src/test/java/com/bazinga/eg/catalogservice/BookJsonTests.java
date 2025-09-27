package com.bazinga.eg.catalogservice;

import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<NewBook> json;

    @Test
    public void newBookSerializationTest() throws IOException {
        NewBook newBook = new NewBook("1234567891", "Title", "Author", 9.99);
        JsonContent<NewBook> jsonContent = json.write(newBook);
        assertThat(jsonContent).hasJsonPathStringValue("@.isbn");
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo("1234567891");

        assertThat(jsonContent).hasJsonPathStringValue("@.title");
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo("Title");

        assertThat(jsonContent).hasJsonPathStringValue("@.author");
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo("Author");

        assertThat(jsonContent).hasJsonPathNumberValue("@.price");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(9.99);
    }

    @Test
    public void newBookDeserializationTest() throws IOException {
        var content = """
                {
                    "isbn": "1234567891",
                    "author": "Author",
                    "title": "Title",
                    "price": 9.99
                }
                """;

        assertThat(json.parse(content)).isEqualTo(new NewBook("1234567891", "Title", "Author", 9.99));
    }
}
