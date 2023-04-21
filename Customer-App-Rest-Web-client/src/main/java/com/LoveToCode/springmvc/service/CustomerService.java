package com.LoveToCode.springmvc.service;

import java.util.List;

import com.LoveToCode.springmvc.model.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveOrUpdate(Customer customer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
}
