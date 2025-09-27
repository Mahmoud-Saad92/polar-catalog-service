package com.bazinga.eg.catalogservice.common.exception.model;

import com.bazinga.eg.catalogservice.common.exception.IExceptionCode;

import java.io.Serial;
import java.io.Serializable;

public class BookAlreadyExistsException extends BookException implements Serializable {

    @Serial
    private static final long serialVersionUID = 5462223100L;

    public BookAlreadyExistsException(IExceptionCode exceptionCode) {
        this(exceptionCode, null);
    }

    public BookAlreadyExistsException(IExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode, cause);
    }

    public BookAlreadyExistsException(IExceptionCode exceptionCode, Throwable cause, String customMessage) {
        super(exceptionCode, cause, customMessage);
    }
}
