package com.bazinga.eg.catalogservice.resource.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EditBook(@NotBlank(message = "The book title must be defined.")
                       String title,
                       @NotBlank(message = "The book author must be defined.")
                       String author,
                       @NotNull(message = "The book price must be defined.")
                       @Positive(message = "The book price must be greater than zero.")
                       Double price) {
}
