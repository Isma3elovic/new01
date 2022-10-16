package com.example.syberproject.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;


@ControllerAdvice
@ToString
public class ApiExceptionHandeler {
    @ExceptionHandler(value = ApiRequestException.class)
    ResponseEntity<Object> handelApiException(ApiRequestException e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                e.getCause(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<Object> handelNotFoundException(NotFoundException e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                e.getCause(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


}
