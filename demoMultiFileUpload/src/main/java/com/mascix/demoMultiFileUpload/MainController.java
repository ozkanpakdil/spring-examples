package com.mascix.demoMultiFileUpload;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public class MainController {
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index.ftl");
	}

	//@PostMapping(value = "/uploadFiles", produces = "application/json")

}
