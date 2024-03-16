package com.example.eventdriven.exception.custom;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {

    HttpStatus  status;
    public BusinessException(String message ) {
        super(message);
    }
    public BusinessException(String message , HttpStatus status) {
        super(message);
        this.status = status;
    }

    public BusinessException(String message ,Throwable cause) {
        super(message, cause);
    }


}
