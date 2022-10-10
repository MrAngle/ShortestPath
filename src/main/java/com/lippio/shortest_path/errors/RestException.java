package com.lippio.shortest_path.errors;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {

    private final Errors errors;

    public RestException(Errors errors) {
        super(errors.getMessage());
        this.errors = errors;
    }

}
