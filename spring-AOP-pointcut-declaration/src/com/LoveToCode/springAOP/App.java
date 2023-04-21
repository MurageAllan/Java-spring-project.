package com.LoveToCode.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.LoveToCode.springDAO.AccountDao;
import com.LoveToCode.springDAO.MembershipDao;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class);
		
		AccountDao account = context.getBean("accountDao",AccountDao.class );
		
		Account theAccount = new Account();
		account.addFamilyAccount(theAccount, true);
		account.balanceAccounts();
		
	
		System.out.println("\n");
		
		MembershipDao membership = context.getBean("membershipDao" , MembershipDao.class);
		
		membership.addPersonalAccount();
		membership.calculateTransactions();
		
		context.close();

	}

}
