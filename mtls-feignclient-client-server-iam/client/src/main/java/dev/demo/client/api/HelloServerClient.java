package dev.demo.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import dev.demo.client.config.SslFeignConfig;

@FeignClient(name = "helloServer", url = "${feign.server.url}", configuration = SslFeignConfig.class)
public interface HelloServerClient {

    @GetMapping("/api/hello")
    String hello();
}
