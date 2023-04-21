package com.LoveToCode.springDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.LoveToCode.springAOP.Account;

@Component
public class AccountDao {
	private String name;
	private String level;

	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getLevel() {
		System.out.println(getClass() + ": in getLevel()");
		return level;
	}

	public void setLevel(String level) {
		System.out.println(getClass() + ": in setLevel()");
		this.level = level;
	}

	public void addFamilyAccount(Account account, boolean vipAcc) {
		System.out.println(getClass() + "Doing my DB work: adding an account");
	}

	public boolean balanceAccounts() {
		System.out.println("Balancing books");
		return false;
	}

	public List<Account> findAccounts(boolean tripWire) {
		
		if(tripWire) {
			throw new  RuntimeException("Not tripping!!");
		}
		System.out.println("This is finding accounts ");
		List<Account> accounts = new ArrayList<>();

		// create accounts.
		Account acc1 = new Account("Mercy", "Platinum");
		Account acc2 = new Account("Maina", "Gold");
		Account acc3 = new Account("Kamau", "Silver");

		// add the accounts to the arraylist
		accounts.add(acc1);
		accounts.add(acc2);
		accounts.add(acc3);

		return accounts;
	}
}
