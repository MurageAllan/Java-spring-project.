package com.LoveToCode.springAOP;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.springDAO.AccountDao;

public class AppAfterThrowing {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);

		AccountDao accountDao = context.getBean("accountDao", AccountDao.class);
		
		List<Account> account = null;
		// Add a try catch
		try {
			// stimulate the exception.
			boolean tripWire = true;
			account = accountDao.findAccounts(tripWire);

		} catch (Exception e) {
			System.out.println("Caught exception is : " + e);
		}
	
		context.close();

	}

}
