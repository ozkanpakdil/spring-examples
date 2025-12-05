package dev.demo.client;

import dev.demo.client.api.HelloServerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupCaller {
    private static final Logger log = LoggerFactory.getLogger(StartupCaller.class);

    @Bean
    CommandLineRunner callServerOnStart(HelloServerClient client) {
        return args -> {
            try {
                String response = client.hello();
                log.info("Received from server: {}", response);
            } catch (Exception e) {
                log.error("Failed to call server: {}", e.getMessage(), e);
            }
        };
    }
}
