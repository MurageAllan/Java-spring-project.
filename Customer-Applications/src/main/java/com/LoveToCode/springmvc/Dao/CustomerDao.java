package com.LoveToCode.springmvc.Dao;

import java.util.List;

import javax.validation.Valid;

import com.LoveToCode.springmvc.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getCustomers();

	public void saveOrUpdate(Customer customer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String searchName);
}
