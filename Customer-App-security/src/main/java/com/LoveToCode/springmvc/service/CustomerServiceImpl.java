package com.LoveToCode.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LoveToCode.springmvc.Dao.CustomerDao;
import com.LoveToCode.springmvc.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Transactional
	@Override
	public List<Customer> getCustomers(int sortFiled) {

		return customerDao.getCustomers(sortFiled);
	}

	@Transactional
	@Override
	public void saveCustomer(Customer customer) {

		customerDao.saveCustomer(customer);

	}

	@Transactional
	@Override
	public Customer getCustomer(Integer theId) {

		Customer customer = customerDao.getCustomer(theId);

		return customer;
	}

	@Transactional
	@Override
	public void deleteCustomer(Integer theId) {

		customerDao.deleteCustomer(theId);

	}
	
	@Transactional
	@Override
	public List<Customer> searchCustomer(String theSearchName) {
		List<Customer> customer = customerDao.searchCustomer(theSearchName);
		return customer;
	}

}
