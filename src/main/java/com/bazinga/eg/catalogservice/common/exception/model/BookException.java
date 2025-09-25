package com.bazinga.eg.catalogservice.common.exception.model;

import com.bazinga.eg.catalogservice.common.exception.IExceptionCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class BookException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 3961091887621543004L;

    private final IExceptionCode exceptionCode;

    public BookException(IExceptionCode exceptionCode) {
        super(exceptionCode.getMessage(), null);
        this.exceptionCode = exceptionCode;
    }

    public BookException(IExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode.getMessage(), cause);
        this.exceptionCode = exceptionCode;
    }

    public BookException(IExceptionCode exceptionCode, Throwable cause, String customMessage) {
        super(customMessage, cause);
        this.exceptionCode = exceptionCode;
    }
}
