package com.LoveToCode.springDAO;

import org.springframework.stereotype.Component;

import com.LoveToCode.springAOP.Account;

@Component
public class AccountDao {
	private String name;
	private String level;
	
	public String getName() {
		System.out.println( getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println( getClass() + ": in setName()");
		this.name = name;
	}

	public String getLevel() {
		System.out.println( getClass() + ": in getLevel()");
		return level;
	}

	public void setLevel(String level) {
		System.out.println( getClass() + ": in setLevel()");
		this.level = level;
	}

	public void addFamilyAccount(Account account , boolean vipAcc) {
		System.out.println(getClass() + "Doing my DB work: adding an account");
	}
	
	public boolean balanceAccounts() {
		System.out.println("Balancing books");
		return false;
	}
}
