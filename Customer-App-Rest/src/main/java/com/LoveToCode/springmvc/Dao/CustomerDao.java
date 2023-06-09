package com.LoveToCode.springmvc.Dao;

import java.util.List;

import com.LoveToCode.springmvc.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(Integer theId);

	public void deleteCustomer(Integer theId);

	public List<Customer> searchCustomer(String theSearchName);
}
