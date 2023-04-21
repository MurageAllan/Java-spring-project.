package com.LoveToCode.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LoveToCode.springboot.entity.Employee;
import com.LoveToCode.springboot.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/list")
	public String listEmployees(Model model) {

		List<Employee> employee = employeeService.findAllByOrderByLastNameAsc();

		model.addAttribute("theEmployee", employee);

		return "employees/list";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Employee employee = new Employee();

		model.addAttribute("theEmployee", employee); // The model attribute binds the form data.
		return "employees/form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId, Model model) {

		Employee employee = employeeService.findById(theId);

		model.addAttribute("theEmployee", employee);
		return "employees/form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("theEmployee") Employee Employee) {

		employeeService.save(Employee);

		// Use a redirect to prevent duplicates.
		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int theId) {

		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}

	@GetMapping("/search")
	public String searchEmployee(@RequestParam("employeeName") String theName, Model model) {

		List<Employee> employee = employeeService.searchBy(theName);

		model.addAttribute("theEmployee", employee);

		return "employees/list";
	}

}
