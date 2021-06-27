package com.mascix.webclientloadbalancer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.client.loadbalancer.reactive.RetryableLoadBalancerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WebClientLoadbalancerApplicationTests {
    @Autowired
    WebClient.Builder webClientBuilder;

//    @Autowired
//    private RetryableLoadBalancerExchangeFilterFunction lbFunction;


    static List<String> lastSearchedIps = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        lastSearchedIps.add("1.1.1.1");
        lastSearchedIps.add("1.1.1.2");
        lastSearchedIps.add("1.1.1.3");
        lastSearchedIps.add("1.1.1.4");
        lastSearchedIps.add("1.1.1.5");
    }

    @Test
    void contextLoads() {
        lastSearchedIps.forEach(i -> {
            String url = "lb://restCaller/get?test=" + i + "&source=test";

            String whois = webClientBuilder
//                    .baseUrl("lb://restCaller")
//                    .filter(lbFunction)
                    .build()
                    .get()
//                    .uri("/whois",param)
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofMinutes(1))
                    .block();
            System.out.println("whois:" + whois);
            assert whois != null;
        });
    }

}
