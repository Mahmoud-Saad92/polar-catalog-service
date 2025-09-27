package com.bazinga.eg.catalogservice;

import com.bazinga.eg.catalogservice.resource.payload.NewBook;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {

    private static Validator validator;
    private static ValidatorFactory validatorFactory;

    @BeforeAll
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void cleanup() {
        if (validatorFactory != null) {
            validatorFactory.close();
        }
    }

    @Test
    public void whenAllFieldsCorrectThenValidationSucceeds() {
        NewBook newBook = new NewBook("1234567891", "Title", "Author", 9.90);
        Set<ConstraintViolation<NewBook>> violations = validator.validate(newBook);
        assertThat(violations).isEmpty();
    }

    @Test
    public void whenIsbnDefinedButIncorrectThenValidationFails() {
        NewBook newBook = new NewBook("a234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<NewBook>> violations = validator.validate(newBook);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }
}
