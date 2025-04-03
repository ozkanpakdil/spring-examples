package com.example.demo.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI applicationOpenApi() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().
                        addList("BearerAuth"))
                .components(new Components().addSecuritySchemes
                        ("BearerAuth", createAPIKeyScheme()))
                .info(new Info()
                        .title("First lab Documentation")
                        .description(
                                "processes: working with user profile - management, publishing news, subscriptions (free and paid)"
                        ).version("1")
                );
    }
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}