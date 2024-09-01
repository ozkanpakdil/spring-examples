package com.example.demo.controllers;

import com.example.demo.config.AppConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRest {

    final
    AppConfig appConfig;

    public AppRest(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping
    public String hello() {
        return appConfig.getEnvValue();
    }
}
