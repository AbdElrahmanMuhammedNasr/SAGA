package com.example.eventdriven.exception.custom;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationException extends RuntimeException {

    HttpStatus  status;
    public ValidationException(String message ) {
        super(message);
    }
    public ValidationException(String message , HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ValidationException(String message , Throwable cause) {
        super(message, cause);
    }


}
