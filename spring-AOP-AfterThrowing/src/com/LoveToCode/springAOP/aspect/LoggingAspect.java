package com.LoveToCode.springAOP.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	@AfterThrowing(pointcut = "execution(* com.LoveToCode.springDAO.AccountDao.findAccounts(..))", throwing = "exc")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, Throwable exc) {

		String methodSignature = joinPoint.getSignature().toShortString();
		System.out.println(methodSignature);

		// logging the exception
		System.out.println("The exception is : " + exc);
	}

	// add new afterReturning advice.
	@AfterReturning(pointcut = "execution(* com.LoveToCode.springDAO.AccountDao.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {
		// print the method we are advising on.

		System.out.println("Running after returning.");
		System.out.println("\n");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		System.out.println("The method is: " + methodSignature);

		System.out.println("\n");

		// print the results
		System.out.println("The result is: " + result);

		// modify the result.
		convertAccountNameToUpperCase(result);

		System.out.println("The result is: " + result);
	}

	private void convertAccountNameToUpperCase(List<Account> result) {
		// loop through the accounts.
		for (Account accounts : result) {

			// get upper case version of account name.
			String upperName = accounts.getName().toUpperCase();

			// update the name of the account.
			accounts.setName(upperName);

		}

	}

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
