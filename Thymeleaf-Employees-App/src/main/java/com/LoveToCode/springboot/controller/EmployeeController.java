package com.LoveToCode.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LoveToCode.springboot.model.Employee;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	List<Employee> employee = new ArrayList<>();
	
	@PostConstruct
	public void loadData() {
		Employee employee1 = new Employee(1,"Nyamu", "Murage", 20, "nyamuallan@gmail.com");
		Employee employee2 = new Employee(2,"Ivy", "Wangeci", 22, "wangeciivy@gmail.com");
		Employee employee3 = new Employee(3,"Duncan", "Otieno", 24, "otienoduncan@gmail.com");
		Employee employee4 = new Employee(4,"John", "Nyaga", 23, "nyagajohn@gmail.com");
		Employee employee5 = new Employee(5,"Stacy", "Joy", 25, "joystacy@gmail.com");
		
		
		employee.add(employee1);
		employee.add(employee2);
		employee.add(employee3);
		employee.add(employee4);
		employee.add(employee5);
		
	}
	@GetMapping("/list")
	public String listEmployees(Model model) {
		
		model.addAttribute("theEmployees", employee);
		
		return "list-Employee";
	}
}
