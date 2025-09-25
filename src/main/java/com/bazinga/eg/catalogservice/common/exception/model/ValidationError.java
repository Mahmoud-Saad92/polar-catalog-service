package com.bazinga.eg.catalogservice.common.exception.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError {

    private final Integer status;
    private final String message;
    private final List<Field> fieldErrors = new ArrayList<>();

    public ValidationError(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public void addFieldError(String field, String defaultMessage) {
        Field error = new Field(field, defaultMessage);
        fieldErrors.add(error);
    }

    public List<Field> getFieldErrors() {
        return new ArrayList<>(fieldErrors);
    }

    public record Field(String field, String defaultMessage) {
    }
}
