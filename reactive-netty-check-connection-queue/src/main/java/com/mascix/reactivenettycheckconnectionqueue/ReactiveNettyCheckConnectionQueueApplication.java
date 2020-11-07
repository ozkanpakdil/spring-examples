package com.mascix.reactivenettycheckconnectionqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactiveNettyCheckConnectionQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveNettyCheckConnectionQueueApplication.class, args);
    }

    @Bean
    public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
        NettyReactiveWebServerFactory webServerFactory = new NettyReactiveWebServerFactory();
        webServerFactory.addServerCustomizers(new NettyConfigure());
        return webServerFactory;
    }
}
