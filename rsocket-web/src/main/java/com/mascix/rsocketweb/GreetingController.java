package com.mascix.rsocketweb;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GreetingController {

    @Autowired
    RSocketRequester.Builder requesterBuilder;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/rsock")
    public void rscokRequest(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        RSocketRequester rsocketRequester = requesterBuilder.tcp("localhost", 9898);
        rsocketRequester
                .route("rsocket/greeting/{name}", "Lokesh")
                .data("Hello there!")
                .retrieveMono(String.class)
                .subscribe(response -> log.info("RSocket response : {}", response));
    }

}