package com.LoveToCode.springDAO;

import org.springframework.stereotype.Component;

import com.LoveToCode.springAOP.Account;

@Component
public class AccountDao {
	
	public void addFamilyAccount(Account account , boolean vipAcc) {
		System.out.println(getClass() + "Doing my DB work: adding an account");
	}
	
	public boolean balanceAccounts() {
		System.out.println("Balancing books");
		return false;
	}
}
