package dev.wayne.numbers.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class ApiExceptionMapper {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        return new ResponseEntity<>(ex.toString(), HttpStatusCode.valueOf(ex.getStatusCode()));
    }
}
