package com.bazinga.eg.catalogservice.common.exception.enums;

import lombok.Getter;

@Getter
public enum ExceptionType {

    DATA("data"),
    BUSINESS("business"),
    REQUEST("request"),
    SERVER("server");

    private final String value;

    ExceptionType(String value) {
        this.value = value;
    }
}
