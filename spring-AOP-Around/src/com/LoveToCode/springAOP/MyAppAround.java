package com.LoveToCode.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.SpringService.TrafficFortuneService;

public class MyAppAround {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);
		
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService" , TrafficFortuneService.class);
		
		String data = fortuneService.getFortune();
		
		System.out.println("The data is: " + data);
		
		System.out.println("This is the main app.");
		
		context.close();

	}

}
