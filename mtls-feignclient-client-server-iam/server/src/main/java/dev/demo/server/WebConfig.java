package dev.demo.server;

import dev.demo.server.security.ClientCertArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ClientCertArgumentResolver clientCertArgumentResolver;

    public WebConfig(ClientCertArgumentResolver clientCertArgumentResolver) {
        this.clientCertArgumentResolver = clientCertArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(clientCertArgumentResolver);
    }
}
