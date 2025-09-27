package com.bazinga.eg.catalogservice.common.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    @JsonIgnore
    private Integer status;

    private String title;

    private String message;

    private String debugMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(Integer status, String title, String message) {
        this();
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public ApiError(Integer status, String title, String message, Throwable ex) {
        this();
        this.status = status;
        this.title = title;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
