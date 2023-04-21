package com.LoveToCode.springAOP.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	// create a logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// add a around advice
	@Around("execution(* com.LoveToCode.SpringService.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// get the joint point method
		String methodSignature = proceedingJoinPoint.getSignature().toShortString();
		myLogger.info("The join point is : " + methodSignature);

		// begin timestamp
		long begin = System.currentTimeMillis();

		// execute the join point method
		Object result = proceedingJoinPoint.proceed();

		// end the timestamp
		long end = System.currentTimeMillis();

		// compute the duration
		long duration = end - begin;

		myLogger.info("The time taken running the join point is: " + duration / 1000 + " seconds");

		// return the result.

		return result;
	}

	// add a afterFinally advice
	@After("execution(* com.LoveToCode.springDAO.AccountDao.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		myLogger.info("Executing after finally");
		String methodSignature = joinPoint.getSignature().toShortString();
		myLogger.info("The method is " + methodSignature);
	}

	// add afterThrowing advice.
	@AfterThrowing(pointcut = "execution(* com.LoveToCode.springDAO.AccountDao.findAccounts(..))", throwing = "exc")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, Throwable exc) {
		myLogger.info("Executing after throwing ");
		String methodSignature = joinPoint.getSignature().toShortString();
		myLogger.info("The method is " + methodSignature);

		// logging the exception
		myLogger.info("The exception is : " + exc);
	}

	// add new afterReturning advice.
	@AfterReturning(pointcut = "execution(* com.LoveToCode.springDAO.AccountDao.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {
		// print the method we are advising on.

		myLogger.info("Running after returning.");
		myLogger.info("\n");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		myLogger.info("The method is: " + methodSignature);

		myLogger.info("\n");

		// print the results
		myLogger.info("The result is: " + result);

		// modify the result.
		convertAccountNameToUpperCase(result);

		myLogger.info("The result is: " + result);
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

		myLogger.info("Logging to the Dao accounts");

		// display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		myLogger.info("Method " + methodSignature);

		// display method arguments.

		// get arguments

		Object[] args = joinPoint.getArgs();

		for (Object theArgs : args) {
			myLogger.info(theArgs.toString());

			if (theArgs instanceof Account) {
				// downcast and print the specific object.
				Account account = (Account) theArgs;

				myLogger.info("The account name : " + account.getName());
				myLogger.info("The account level : " + account.getLevel());
			}
		}

	}

}
