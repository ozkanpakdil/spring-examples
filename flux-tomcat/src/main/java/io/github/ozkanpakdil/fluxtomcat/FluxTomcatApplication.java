package io.github.ozkanpakdil.fluxtomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FluxTomcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluxTomcatApplication.class, args);
	}

	@Bean
	public WebClient webClient(WebClient.Builder webClientBuilder) {
		return webClientBuilder.build();
	}
}
