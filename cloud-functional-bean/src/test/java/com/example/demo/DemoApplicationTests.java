package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
@FunctionalSpringBootTest
@AutoConfigureWebTestClient
class DemoApplicationTests {

    @Autowired
    private WebTestClient client;

    @Test
    void functionalBeanPostTest() {
        client.post().uri("/")
                .body(Mono.just("foo"), String.class).exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(System.out::println)
                .isEqualTo("FOO");
    }
    @Test
    void functionalBeanPutTest() {
        client.put().uri("/")
                .body(Mono.just("foo"), String.class).exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(System.out::println)
                .isEqualTo("FOO");
    }
    @Test
    void functionalBeanGetTest() {
        // NOTE: GET function does not work with functional beans or I could not find a way to call it.
        client.get().uri("/?foo")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(System.out::println)
                .isEqualTo("");
    }

}
