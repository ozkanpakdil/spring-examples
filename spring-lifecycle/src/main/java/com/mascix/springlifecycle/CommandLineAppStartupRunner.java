package com.mascix.springlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info(Arrays.toString(args));
    }
}