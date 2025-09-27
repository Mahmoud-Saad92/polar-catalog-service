package com.bazinga.eg.catalogservice.common.exception;

import com.bazinga.eg.catalogservice.common.exception.enums.ExceptionType;

public interface IExceptionCode {
    ExceptionType getType();

    Integer getStatus();

    String getTitle();

    String getMessage();
}
