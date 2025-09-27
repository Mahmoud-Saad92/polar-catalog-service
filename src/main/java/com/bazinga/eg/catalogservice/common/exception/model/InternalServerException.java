package com.bazinga.eg.catalogservice.common.exception.model;

import com.bazinga.eg.catalogservice.common.exception.IExceptionCode;

import java.io.Serial;
import java.io.Serializable;

public class InternalServerException extends BookException implements Serializable {

    @Serial
    private static final long serialVersionUID = 5462223300L;

    protected InternalServerException() {
        this(null);
    }

    public InternalServerException(IExceptionCode exceptionCode) {
        this(exceptionCode, null);
    }

    public InternalServerException(IExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode, cause);
    }

    public InternalServerException(IExceptionCode exceptionCode, Throwable cause, String customMessage) {
        super(exceptionCode, cause, customMessage);
    }
}
