package com.mascix.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class Greetings {
//    @GetMapping("/")
//    public String greeting() {
//        return "Hello, World";
//    }

    @GetMapping("/")
    public Map<String, String> greeting2() {
        return Collections.singletonMap("message", "Hello, World");
    }

    @GetMapping("/{name}")
    public String greeting(@PathVariable String name) {
        return "Hello, " + name;
    }
}
