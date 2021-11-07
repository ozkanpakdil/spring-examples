package com.mascix.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greetings {
    @GetMapping("/")
    public String greeting() {
        return "Hello, World";
    }
}
