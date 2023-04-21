package com.LoveToCode.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.LoveToCode.springmvc.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	@Value("${crm.rest.url}")
	private String crmRestUrl;

	@Override
	public List<Customer> getCustomers() {

		ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Customer>>() {
				});

		List<Customer> customers = responseEntity.getBody();

		return customers;
	}

	@Override
	public void saveOrUpdate(Customer customer) {

		if (customer.getid() == 0) {
			restTemplate.postForEntity(crmRestUrl, customer, String.class);
		} else {
			restTemplate.put(crmRestUrl, customer);
		}

	}

	@Override
	public Customer getCustomer(int theId) {

		Customer customer = restTemplate.getForObject(crmRestUrl + "/" + theId, Customer.class);

		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {

		restTemplate.delete(crmRestUrl + "/" + theId);

	}

}
