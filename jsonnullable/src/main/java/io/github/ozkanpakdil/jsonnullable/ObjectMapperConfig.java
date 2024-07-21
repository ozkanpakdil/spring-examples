package io.github.ozkanpakdil.jsonnullable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper(com.fasterxml.jackson.databind.Module jsonNullableBooleanModule) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(jsonNullableBooleanModule);
        return objectMapper;
    }
}