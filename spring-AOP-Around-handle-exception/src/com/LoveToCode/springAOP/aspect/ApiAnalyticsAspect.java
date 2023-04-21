package com.LoveToCode.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(2)
public class ApiAnalyticsAspect {
	
	@Before("com.LoveToCode.springAOP.aspect.AppAspects.forPackageDaoNoGettersSetters()")
	public void performApiAnalytics() {

		System.out.println("Perform analytics before Dao acccounts.");
	}
}
