package com.example.eventdriven.exception;

import com.example.eventdriven.exception.custom.BusinessException;
import com.example.eventdriven.exception.custom.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException e){
        ExceptionPayload build = ExceptionPayload
                .builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .zonedDateTime(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity<>(build, e.getStatus());
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException e){
        ExceptionPayload build = ExceptionPayload
                .builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .zonedDateTime(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return new ResponseEntity<>(build, e.getStatus());
    }
}
