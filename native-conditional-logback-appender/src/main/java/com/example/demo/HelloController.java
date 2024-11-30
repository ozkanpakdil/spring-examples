package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        logger.info("hello request");
        return "hello";
    }
}
