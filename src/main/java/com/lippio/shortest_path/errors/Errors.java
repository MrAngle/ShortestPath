package com.lippio.shortest_path.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Errors {
    COUNTRY_NOT_FOUND("Country not found", HttpStatus.NOT_FOUND),
    PATH_NOT_FOUND("Land path not found", HttpStatus.BAD_REQUEST),
    INCORRECT_COUNTRY_LIST_FORMAT("Incorrect country list format", HttpStatus.BAD_REQUEST),
    FETCHING_COUNTRY_INTERNAL_ERROR("Unable to fetch country information", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    Errors(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }
}
