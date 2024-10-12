package io.github.ozkanpakdil.scheduled_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduledDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledDemoApplication.class, args);
	}

}
