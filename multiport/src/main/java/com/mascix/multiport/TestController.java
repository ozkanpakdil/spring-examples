package com.mascix.multiport;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    String hello(HttpServletRequest request) {
        return "hello from " + request.getLocalPort();
    }
}
