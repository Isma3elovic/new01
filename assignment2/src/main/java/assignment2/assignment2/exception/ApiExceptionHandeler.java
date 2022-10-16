package assignment2.assignment2.exception;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;


@ControllerAdvice

public class ApiExceptionHandeler {
    public ApiExceptionHandeler() {
    }

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


    @ExceptionHandler(value = ChangeSetPersister.NotFoundException.class)
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
