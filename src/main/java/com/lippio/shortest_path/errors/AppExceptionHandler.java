package com.lippio.shortest_path.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<DTOResponse> errorHandler(RestException exception) {
        return new ResponseEntity<>(DTOResponse.builder().message(exception.getMessage()).status(exception.getErrors().getStatus().value())
                .build(), exception.getErrors().getStatus());
    }

}
