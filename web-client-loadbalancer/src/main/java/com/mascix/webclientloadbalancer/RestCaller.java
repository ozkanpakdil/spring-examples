package com.mascix.webclientloadbalancer;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class RestCaller implements ServiceInstanceListSupplier {

    private final String serviceId;

    public RestCaller(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
//                new DefaultServiceInstance(serviceId + "1", serviceId, "mascix.com", 80, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "httpbin.org", 80, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "httpbin.org", 80, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "httpbin.org", 80, false)
//                new DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 9999, false)
        ));
    }
}
