package com.LoveToCode.springboot.restcontroller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

	@Value("${coach.name}")
	private String coachName;

	@Value("${team.name}")
	private String teamName;

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

	@GetMapping("/teamInfo")
	public String teamInfo() {

		return "The coach name is " + coachName + " and the team is " + teamName;
	}

}
