package com.LoveToCode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DemoController {

	@GetMapping("hello")
	public String sayHello(Model model) {

		String greetings = "Hello world!";
		model.addAttribute("greeting", greetings);
		
		model.addAttribute("theDate", new java.util.Date());

		return "hello"; // spring-boot auto-configures to use thymeleaf, looks in the resources/templates for
						// "hello.html"
	}
}
