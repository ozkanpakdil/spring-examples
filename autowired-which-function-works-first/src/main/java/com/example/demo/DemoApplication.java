package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.Nullable;

@SpringBootApplication
public class DemoApplication {

	
	@Autowired
	public void z3() throws Exception {
		functionNameTeller();
	}
	
	@Autowired
	public void a2() throws Exception {
		functionNameTeller();
	}

	@Autowired
	public void a3() throws Exception {
		functionNameTeller();
	}

	@Autowired
	public void a4() throws Exception {
		functionNameTeller();
	}

	@Autowired
	public void a5() throws Exception {
		functionNameTeller();
	}

	@Autowired
	public void a6() throws Exception {
		functionNameTeller();
	}

	public static void functionNameTeller() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		System.out.println(stackTrace[2].getMethodName());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
