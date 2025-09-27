package com.bazinga.eg.catalogservice.common.exception.model;

import com.bazinga.eg.catalogservice.common.exception.IExceptionCode;

import java.io.Serial;
import java.io.Serializable;

public class BookNotFoundException extends BookException implements Serializable {

    @Serial
    private static final long serialVersionUID = 5462223201L;

    public BookNotFoundException(IExceptionCode exceptionCode) {
        this(exceptionCode, null);
    }

    public BookNotFoundException(IExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode, cause);
    }

    public BookNotFoundException(IExceptionCode exceptionCode, Throwable cause, String customMessage) {
        super(exceptionCode, cause, customMessage);
    }
}
