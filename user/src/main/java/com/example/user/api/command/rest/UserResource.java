package com.example.user.api.command.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    @GetMapping("/user/hello")
    public String get() {
        return "Hello World!";
    }
}
