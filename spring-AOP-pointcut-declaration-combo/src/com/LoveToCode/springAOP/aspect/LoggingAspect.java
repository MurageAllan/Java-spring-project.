package com.LoveToCode.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	// @Before("execution(public void add*()) ")

	// create a pointcut for getter methods.
	@Pointcut("execution(* com.LoveToCode.springDAO.*.get*(..))")
	private void forGetters() {
	}

	// create a pointcut for setter methods.
	@Pointcut("execution(* com.LoveToCode.springDAO.*.set*(..))")
	private void forSetters() {
	}

	// create pointcut include package and exclude getters and setters
	@Pointcut("forpackageDao() && !(forSetters() || forGetters() )")
	private void forPackageDaoNoGettersSetters() {
	}

	@Pointcut("execution(* com.LoveToCode.springDAO.*.*(..))")
	private void forpackageDao() {
	}

	@Before("forPackageDaoNoGettersSetters()")
	public void beforeAddAccountAdvice() {

		System.out.println("Executing @Before advice on add account");
	}

	@Before("forPackageDaoNoGettersSetters()")
	public void performApiAnalytics() {

		System.out.println("Perform analytics before Dao acccounts.");
	}
}
