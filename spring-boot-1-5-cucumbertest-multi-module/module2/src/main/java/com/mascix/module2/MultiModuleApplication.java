package com.mascix.module2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class MultiModuleApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MultiModuleApplication.class, args);
	}

}
