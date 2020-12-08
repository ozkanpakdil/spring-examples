package com.mascix.multiport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller1")
public class TestController1 {

    @GetMapping("/hello")
    String hello(HttpServletRequest request) {
        return "hello from controller 1 " + request.getLocalPort();
    }
}
