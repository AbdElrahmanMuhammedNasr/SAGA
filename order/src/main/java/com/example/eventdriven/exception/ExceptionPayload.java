package com.example.eventdriven.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExceptionPayload {
        String message;
        HttpStatus status;
        ZonedDateTime zonedDateTime ;

}
