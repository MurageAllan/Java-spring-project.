package com.LoveToCode.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LoggingAspect {


	@Before("com.LoveToCode.springAOP.aspect.AppAspects.forPackageDaoNoGettersSetters()")
	public void beforeAddAccountAdvice() {

		System.out.println("Logging to the Dao accounts");
	}

	
}
