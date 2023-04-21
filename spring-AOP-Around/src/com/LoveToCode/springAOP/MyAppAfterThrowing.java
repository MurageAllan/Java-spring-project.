package com.LoveToCode.springAOP;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.springDAO.AccountDao;

public class MyAppAfterThrowing {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);

		AccountDao accountDao = context.getBean("accountDao", AccountDao.class);
		List<Account> account = null;

		try {
			boolean tripWire = true;
			account = accountDao.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("The exception is : " + e);
		}
		System.out.println("The account list is " + account);
		context.close();

	}

}
