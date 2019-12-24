package com.mascix.springlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class SpringLifecycleApplicationTests {

    @Autowired
    AppStartupListener startupListener;

    @Test
    void testAppstartupListener() {
        String actual = startupListener.justTesting();
        assertNotNull(actual);
        log.info("Success:" + actual);
    }

}
