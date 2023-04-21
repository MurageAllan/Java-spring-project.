package com.LoveToCode.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	// put all our related advices 
	
	//@Before("execution(public void add*()) ")
	
	@Before("execution(* com.LoveToCode.springDAO.*.*(..))")
	public void beforeAddAccountAdvice() {
		
		
		System.out.println("Executing @Before advice on add account");
	}
}
