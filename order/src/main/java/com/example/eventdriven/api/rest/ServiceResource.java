package com.example.eventdriven.api.rest;


import com.example.eventdriven.config.ServiceInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceResource {

     ServiceInfo serviceInfo;

    @GetMapping("/service/info")
    public ServiceInfo serviceInfo() {
            return serviceInfo;
        }
}
