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
        if (request.getLocalPort() == 8888) {
            return service.hellofrom8888();
        }
        if (request.getLocalPort() == 8889) {
            return service.hellofrom8889();
        }

        return "no repsonse ";
    }
}
