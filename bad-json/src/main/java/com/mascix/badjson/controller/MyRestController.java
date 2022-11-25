package com.mascix.badjson.controller;

import com.mascix.badjson.service.BadJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/")
public class MyRestController {
    @Autowired
    BadJsonService service;

    @GetMapping
    public Map badJson() {
        return service.gimmeBadMap();
    }
}
