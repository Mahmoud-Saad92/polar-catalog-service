package com.bazinga.eg.catalogservice.common.exception.model;

import com.bazinga.eg.catalogservice.common.exception.IExceptionCode;

import java.io.Serial;
import java.io.Serializable;

public class BadRequestException extends BookException implements Serializable {

    @Serial
    private static final long serialVersionUID = 5572223130L;

    protected BadRequestException() {
        this(null);
    }

    public BadRequestException(IExceptionCode exceptionCode) {
        this(exceptionCode, null);
    }

    public BadRequestException(IExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode, cause);
    }

    public BadRequestException(IExceptionCode exceptionCode, Throwable cause, String customMessage) {
        super(exceptionCode, cause, customMessage);
    }
}
