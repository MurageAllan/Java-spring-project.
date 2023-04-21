package com.LoveToCode.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LoveToCode.springmvc.entity.Customer;
import com.LoveToCode.springmvc.service.CustomerService;
import com.LoveToCode.springmvc.util.SortUtil;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("list")
	public String list( @RequestParam(required = false) String sort ,Model model) {

		List<Customer> customers = null;
		
		if(sort != null) {
			int theSortField = Integer.parseInt(sort);
			
			customers = customerService.getCustomers(theSortField);
		}
		else {
			// sort by default that is the last name.
			customers = customerService.getCustomers(SortUtil.LASTNAME);
		}

		model.addAttribute("theCustomers", customers);

		return "list";
	}

	@GetMapping("/showFormAdd")
	public String showForm(Model model) {

		Customer customer = new Customer();

		model.addAttribute("theCustomer", customer);
		return "form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("theCustomer") Customer customer) {

		customerService.saveCustomer(customer);

		return "redirect:/customer/list";
	}

	@GetMapping("/update")
	public String update(@RequestParam("Id") Integer theId, Model model) {

		Customer customer = customerService.getCustomer(theId);

		model.addAttribute("theCustomer", customer);
		return "form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("Id") Integer theId) {

		customerService.deleteCustomer(theId);

		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("theSearchName") String theSearchName, Model model) {
		List<Customer> customer = customerService.searchCustomer(theSearchName);

		model.addAttribute("theCustomers", customer);
		return "list";
	}

}
