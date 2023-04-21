package com.LoveToCode.springmvc.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LoveToCode.springmvc.Dao.CustomerDao;
import com.LoveToCode.springmvc.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	@Transactional
	public List<Customer> getCustomers() {

		return customerDao.getCustomers();
	}

	@Override
	@Transactional
	public void saveOrUpdateCustomer(Customer customer) {

		customerDao.saveOrUpdate(customer);

	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {

		return customerDao.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		customerDao.deleteCustomer(theId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String searchName) {
		
		return customerDao.searchCustomer(searchName);
	}

}
