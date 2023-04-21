package com.LoveToCode.springmvc.service;

import java.util.List;

import javax.validation.Valid;

import com.LoveToCode.springmvc.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveOrUpdateCustomer(Customer customer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String searchName);
}
