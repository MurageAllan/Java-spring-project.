package com.LoveToCode.springmvc.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LoveToCode.springmvc.entity.Customer;
import com.LoveToCode.springmvc.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// inject the customer service
	@Autowired
	private CustomerService customerService;

	// list all customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {

		return customerService.getCustomers();
	}

	// get a single a customer
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer customer = customerService.getCustomer(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("The customer is not found " + customerId);
		} else {
			return customer;
		}

	}

	// create a new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		
	
		customerService.saveCustomer(customer);

		return customer;
	}

	// update a customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {

		customerService.saveCustomer(customer);

		return customer;
	}

	// delete a customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		Customer customer = customerService.getCustomer(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("customer id is not found " + customerId);
		} else {
			customerService.deleteCustomer(customerId);

			return "Deleted customer id - " + customerId;
		}
	}
}
