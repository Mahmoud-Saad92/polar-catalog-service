package com.bazinga.eg.catalogservice.common.exception.enums;

import com.bazinga.eg.catalogservice.common.exception.IExceptionCode;
import org.springframework.http.HttpStatus;

public enum ExceptionCode implements IExceptionCode {
    NO_DATA_FOUND(ExceptionType.DATA, HttpStatus.NOT_FOUND.value(), "No data found", "The request input parameter retrieve no data, Check the input parameter and try again"),
    DUPLICATE_DATA_FOUND(ExceptionType.BUSINESS, HttpStatus.UNPROCESSABLE_ENTITY.value(), "Duplicate data found", "The request input parameter retrieve a duplicate data, Check the input parameter and try again"),
    INVALID_PAYLOAD(ExceptionType.REQUEST, HttpStatus.BAD_REQUEST.value(), "Invalid payload data", "The request payload data is incorrect, Check the payload body and try again"),
    UN_EXPECTED_ERROR(ExceptionType.SERVER, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal error", "The server encountered an internal error, Please retry the request");

    private final ExceptionType exceptionType;
    private final Integer httpStatus;
    private final String title;
    private final String detail;

    ExceptionCode(ExceptionType exceptionType, Integer httpStatus, String title, String detail) {
        this.exceptionType = exceptionType;
        this.httpStatus = httpStatus;
        this.title = title;
        this.detail = detail;
    }

    @Override
    public ExceptionType getType() {
        return this.exceptionType;
    }

    @Override
    public Integer getStatus() {
        return this.httpStatus;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.detail;
    }
}
