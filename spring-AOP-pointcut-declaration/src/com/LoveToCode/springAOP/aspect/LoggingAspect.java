package com.LoveToCode.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	// put all our related advices 
	
	//@Before("execution(public void add*()) ")
	
	@Pointcut("execution(* com.LoveToCode.springDAO.*.*(..))")
	private void forpackageDao() {};
	
	
	@Before("forpackageDao()")
	public void beforeAddAccountAdvice() {
		
		
		System.out.println("Executing @Before advice on add account");
	}
	
	@Before("forpackageDao()")
	public void performApiAnalytics() {
		
		System.out.println("Perform analytics before Dao acccounts.");
	}
}
