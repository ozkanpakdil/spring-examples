package com.mascix.webfluxipwhitelist;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@EnableWebFluxSecurity
public class WebSecurityConfig {

    ArrayList<String> whiteListIp = new ArrayList();

    public WebSecurityConfig() {
        whiteListIp.add("0:0:0:0:0:0:0:1");
        whiteListIp.add("192.168.1.1");
        whiteListIp.add("127.0.0.1");
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange()
                .access(this::whiteListIp)
                .and()
                .httpBasic();

        return http.build();
    }

    private Mono<AuthorizationDecision> whiteListIp(Mono<Authentication> authentication, AuthorizationContext context) {
        String ip = context.getExchange().getRequest().getRemoteAddress().getAddress().toString().replace("/", "");
//        System.out.println("IP:" + ip);
//        System.out.println("IP:" + ((whiteListIp.contains(ip)) ? true : false));
        return authentication.map((a) -> new AuthorizationDecision(a.isAuthenticated()))
                .defaultIfEmpty(new AuthorizationDecision(
                        (whiteListIp.contains(ip)) ? true : false
                ));
    }

}
