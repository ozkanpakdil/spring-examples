package com.mascix.reactivenettycheckconnectionqueue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    String hello() throws InterruptedException {
        //        Thread.sleep(1000 * 60 * 60);  //sleeping the thread in order to overflow netty.

        return "hello world\n";
    }

}
