package com.mascix.getport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    ServletWebServerApplicationContext ctx;

    @GetMapping("/port")
    public int printPort() {
        return ctx.getWebServer().getPort();
    }
}
