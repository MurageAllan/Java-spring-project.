package com.LoveToCode.springboot.restcontroller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	
	
	
	@GetMapping("/")
	public String helloWorld() {
		return "Hello world! time on the server is " + LocalDateTime.now();
	}
	
	@GetMapping("/fun")
	public String helloWorld1() {
		return "It is fun to be out here";
	}
	@GetMapping("/football")
	public String helloWorld2() {
		return "I am a footbal fanatic";
	}
	
}
