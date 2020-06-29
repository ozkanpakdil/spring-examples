package com.mascix.multimodule1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controller1 {
	@GetMapping("/module1")
	@ResponseBody
	public String home() {
		return "hello module 1";
	}
}
