package com.LoveToCode.springboot;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		System.out.println("DemoApplication initialized");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("Post construct method ");
	}

}
