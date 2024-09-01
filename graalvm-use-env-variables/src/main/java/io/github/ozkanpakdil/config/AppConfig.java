package io.github.ozkanpakdil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${app.env.value}")
    private String envValue;

    public String getEnvValue() {
        return envValue;
    }
}
