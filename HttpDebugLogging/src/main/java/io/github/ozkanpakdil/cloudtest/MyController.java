package io.github.ozkanpakdil.cloudtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {
    private final RestClient client;
    private final RestTemplate restTemplate;

    public MyController(RestClient client, RestTemplate restTemplate) {
        this.client = client;
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/hello", produces = "application/json")
    public String hello() {
        return client.get().uri("http://httpbin.org/get?test=2")
                .retrieve().body(String.class);
    }

    @GetMapping(path = "/hello2", produces = "application/json")
    public String hello2() {
        return restTemplate.getForObject("http://httpbin.org/get?test=1", String.class);
    }
}
