package com.lippio.shortest_path.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Errors {
    COUNTRY_NOT_FOUND("Country not found", HttpStatus.BAD_REQUEST),
    PATH_NOT_FOUND("Path not found", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    Errors(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }
}
