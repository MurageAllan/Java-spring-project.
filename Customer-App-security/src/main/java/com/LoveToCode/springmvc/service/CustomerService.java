package com.LoveToCode.springmvc.service;

import java.util.List;

import com.LoveToCode.springmvc.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers(int sortField);

	public void saveCustomer(Customer customer);

	public Customer getCustomer(Integer theId);

	public void deleteCustomer(Integer theId);

	public List<Customer> searchCustomer(String theSearchName);
}
