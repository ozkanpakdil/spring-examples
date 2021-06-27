package com.mascix.webclientloadbalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestCallerConfiguration {

    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext ctx) {
        return ServiceInstanceListSupplier
                .builder()
                .withRetryAwareness()
                .withHealthChecks()
                .withBase(new RestCaller("restCaller"))
                .build(ctx)
                ;
    }

    @Autowired
    WebClient.Builder webClientBuilder;

//    @Bean
//    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
//        return ServiceInstanceListSupplier
//                .builder()
//                .withDiscoveryClient()
//                .withRetryAwareness()
//                .withHealthChecks()
//                .withBase(new WhoisCaller("whoisCaller"))
//                .build(context);
//    }

}