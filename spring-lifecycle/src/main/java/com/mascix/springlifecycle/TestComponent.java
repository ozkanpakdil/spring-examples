package com.mascix.springlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class TestComponent {
    public String getSomeText() {
        return UUID.randomUUID().toString();
    }
}
