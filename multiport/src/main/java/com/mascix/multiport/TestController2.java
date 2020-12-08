package com.mascix.multiport;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/controller2")
public class TestController2 {

    @GetMapping("/hello")
    String hello(HttpServletRequest request) {
        return "hello from controller 2 " + request.getLocalPort();
    }
}
