package com.mascix.module2;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller2 {
	@GetMapping("/module2")
	public String home() {
	  return "hello module 2";
	}
}
