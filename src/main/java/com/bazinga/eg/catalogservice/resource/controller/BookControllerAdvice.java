package com.bazinga.eg.catalogservice.resource.controller;

import com.bazinga.eg.catalogservice.common.exception.model.ApiError;
import com.bazinga.eg.catalogservice.common.exception.model.BookAlreadyExistsException;
import com.bazinga.eg.catalogservice.common.exception.model.BookNotFoundException;
import com.bazinga.eg.catalogservice.common.exception.model.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class BookControllerAdvice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        var errors = new LinkedHashMap<String, String>();
//
//        ex.getAllErrors().forEach(error -> {
//            String field = ((FieldError) error).getField();
//            String defaultMessage = error.getDefaultMessage();
//            errors.put(field, defaultMessage);
//        });
//
//        return errors;
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "VALIDATION FAILED");

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            validationError.addFieldError(field, defaultMessage);
        });

        return new ResponseEntity<>(validationError, HttpStatus.valueOf(validationError.getStatus()));
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiError> handleBookNotFoundException(BookNotFoundException resourceNotFoundException) {
        return buildResponseEntity(new ApiError(resourceNotFoundException.getExceptionCode().getStatus(),
                resourceNotFoundException.getExceptionCode().getTitle(),
                resourceNotFoundException.getMessage()));
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleBookAlreadyExistsException(BookAlreadyExistsException resourceNotFoundException) {
        return buildResponseEntity(new ApiError(resourceNotFoundException.getExceptionCode().getStatus(),
                resourceNotFoundException.getExceptionCode().getTitle(),
                resourceNotFoundException.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralException(Exception exception) {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "General Exception",
                exception.getMessage()));
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        log.debug("exception occurs: {}", apiError);

        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
