package com.bazinga.eg.catalogservice.common.exception;

import com.bazinga.eg.catalogservice.common.exception.model.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExceptionFactory {

    public static BookException create(IExceptionCode exceptionCode) {
        return switch (exceptionCode.getType()) {
            case DATA -> new BookNotFoundException(exceptionCode);
            case REQUEST -> new BadRequestException(exceptionCode);
            case BUSINESS -> new BookAlreadyExistsException(exceptionCode);
            default -> new InternalServerException(exceptionCode);
        };
    }

    public static BookException create(IExceptionCode exceptionCode, Throwable throwable) {
        return switch (exceptionCode.getType()) {
            case DATA -> new BookNotFoundException(exceptionCode, throwable);
            case REQUEST -> new BadRequestException(exceptionCode, throwable);
            case BUSINESS -> new BookAlreadyExistsException(exceptionCode, throwable);
            default -> new InternalServerException(exceptionCode, throwable);
        };
    }

    public static BookException create(IExceptionCode exceptionCode, Throwable throwable, String customMessage) {
        return switch (exceptionCode.getType()) {
            case DATA -> new BookNotFoundException(exceptionCode, throwable, customMessage);
            case REQUEST -> new BadRequestException(exceptionCode, throwable, customMessage);
            case BUSINESS -> new BookAlreadyExistsException(exceptionCode, throwable, customMessage);
            default -> new InternalServerException(exceptionCode, throwable, customMessage);
        };
    }
}
