package com.mascix.rsocketweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class RSocketController {

    @MessageMapping("uppercase")
    public Mono<String> uppercase(@DestinationVariable("input") String input) {
        log.info("input:{}",input);
        return Mono.just(input.toUpperCase());
    }

    @MessageMapping("greeting/{name}")
    public Mono<String> greet(@DestinationVariable("name") String name, Mono<String> greetingMono) {
        return greetingMono
                .doOnNext(greeting ->
                        log.info("Received a greeting from {} : {}", name, greeting))
                .map(greeting -> "Hello "+ name +"!");
    }
}
