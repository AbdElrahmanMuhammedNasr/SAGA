package com.example.eventdriven.api.rest;


import com.example.eventdriven.config.ServiceInfo;
import com.example.eventdriven.exception.custom.BusinessException;
import com.example.eventdriven.exception.custom.ValidationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceResource {

     ServiceInfo serviceInfo;

    @GetMapping("/service/info")
    public ServiceInfo serviceInfo() {
            return serviceInfo;
        }

    @GetMapping("/ex")
    public void exception() {
        boolean b = new Random().nextBoolean();
        if(b)
            throw new ValidationException("Validation ex", HttpStatus.BAD_REQUEST);
        throw new BusinessException("Business ex", HttpStatus.NOT_ACCEPTABLE);
    }
}
