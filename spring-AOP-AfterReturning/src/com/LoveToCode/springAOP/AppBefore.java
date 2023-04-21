package com.LoveToCode.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.springDAO.AccountDao;
import com.LoveToCode.springDAO.MembershipDao;

public class AppBefore {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);
		
		// account 
		AccountDao account = context.getBean("accountDao",AccountDao.class );
		
		Account theAccount = new Account();
		account.addFamilyAccount(theAccount, true);
		
		System.out.println("\n");
		
		account.balanceAccounts();
		
		System.out.println("\n");
	
		// call getters and setters.
		account.setName("Equity");
		account.setLevel("Longterm");
		account.getLevel();
		account.getName();
		
		// membership.
		System.out.println("\n");
		
		MembershipDao membership = context.getBean("membershipDao" , MembershipDao.class);
		
		membership.addPersonalAccount();
		
		
		System.out.println("\n");
		
		membership.calculateTransactions();
		

		context.close();

	}

}
