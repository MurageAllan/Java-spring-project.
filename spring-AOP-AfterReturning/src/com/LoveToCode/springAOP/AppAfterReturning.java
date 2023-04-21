package com.LoveToCode.springAOP;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.springDAO.AccountDao;

public class AppAfterReturning {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);

		AccountDao accountDao = context.getBean("accountDao", AccountDao.class);

		List<Account> account = accountDao.findAccounts(false);

		System.out.println("The account list is " + account);
		
		context.close();

	}

}
