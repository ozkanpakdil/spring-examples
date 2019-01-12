package com.mascix.controller;

import com.mascix.MaintenanceRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {
    @Autowired
    MaintenanceRequestMatcher maintenanceRequestMatcher;

    @RequestMapping("/hello")
    String hello() {
        return "huhuuu";
    }

    @RequestMapping("/maintain/enable")
    void enablemaintanince() {
        maintenanceRequestMatcher.maintainenceMode = true;
    }

    @RequestMapping("/maintain/disable")
    void disablemaintanince() {
        maintenanceRequestMatcher.maintainenceMode = false;
    }

    @RequestMapping("/maintain/status")
    Object status() {
        return maintenanceRequestMatcher.maintainenceMode;
    }

}
