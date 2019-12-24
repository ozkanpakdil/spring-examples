package com.mascix.eurekaserver;

import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableEurekaServer
@SpringBootApplication
public class EurekaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserverApplication.class, args);
    }

}

@RestController
class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    ApplicationInfoManager applicationInfoManager;

    @RequestMapping("/test")
    public List<ServiceInstance> serviceInstancesByApplicationName() {
        System.out.println(applicationInfoManager.getInfo());

        System.out.println(discoveryClient.description());
        System.out.println(discoveryClient.getServices());
        System.out.println(discoveryClient.getInstances(""));
        return this.discoveryClient.getInstances("");
    }
}