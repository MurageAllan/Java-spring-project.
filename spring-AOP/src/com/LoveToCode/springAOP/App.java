package com.LoveToCode.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.springDAO.AccountDao;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);
		
		
		AccountDao account = context.getBean("accountDao" , AccountDao.class);
		
		Account theAccount = new Account();
		account.addFamilyAccount(theAccount, false);
		account.balanceAccounts();
		
		context.close();
		

	}

}
