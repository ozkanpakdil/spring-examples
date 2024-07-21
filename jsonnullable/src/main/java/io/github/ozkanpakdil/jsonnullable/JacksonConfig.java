package io.github.ozkanpakdil.jsonnullable;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Module jsonNullableBooleanModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(JsonNullable.class, new CustomBooleanJsonNullableDeserializer());
        return module;
    }
}