package com.LoveToCode.springAOP;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.SpringService.TrafficFortuneService;

public class MyAppLogger {
	// define customized output stream.
	private static Logger myLogger = Logger.getLogger(MyAppLogger.class.getName());

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);

		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		String data = fortuneService.getFortune();

		myLogger.info("The data is: " + data);

		myLogger.info("This is the main app.");

		context.close();

	}

}
