package com.LoveToCode.springAOP.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.LoveToCode.springAOP.Account;

@Aspect
@Component
@Order(1)
public class LoggingAspect {

	@Before("com.LoveToCode.springAOP.aspect.AppAspects.forPackageDaoNoGettersSetters()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {

		System.out.println("Logging to the Dao accounts");

		// display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		System.out.println("Method " + methodSignature);

		// display method arguments.

		// get arguments

		Object[] args = joinPoint.getArgs();

		for (Object theArgs : args) {
			System.out.println(theArgs);

			if (theArgs instanceof Account) {
				// downcast and print the specific object.
				Account account = (Account) theArgs;

				System.out.println("The account name : " + account.getName());
				System.out.println("The account level : " + account.getLevel());
			}
		}

	}

}
