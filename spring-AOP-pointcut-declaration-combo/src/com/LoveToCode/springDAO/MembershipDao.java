package com.LoveToCode.springDAO;

import org.springframework.stereotype.Component;

@Component
public class MembershipDao {

	public boolean addPersonalAccount() {
		System.out.println(getClass() + " I am  adding a membership account.");

		return true;
	}
	
	public void calculateTransactions() {
		System.out.println("Calculate all the transactions.");
	}
}
