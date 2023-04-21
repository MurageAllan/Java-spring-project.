package com.LoveToCode.springmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LoveToCode.springmvc.entity.Customer;
import com.LoveToCode.springmvc.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// inject a service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model) {

		List<Customer> customers = customerService.getCustomers();

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
	public String addCustomer(@Valid @ModelAttribute("theCustomer") Customer customer, BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			return "form";
		} else {
			customerService.saveOrUpdateCustomer(customer);

			return "redirect:list";
		}

	}

	@GetMapping("/update")
	public String updateCustomer(@RequestParam("Id") int theId, Model model) {

		Customer customer = customerService.getCustomer(theId);

		model.addAttribute("theCustomer", customer);
		return "form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("Id") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:list";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String searchName, Model model) {

		List<Customer> customer = customerService.searchCustomer(searchName);

		model.addAttribute("theCustomers", customer);
		return "list";
	}
}
