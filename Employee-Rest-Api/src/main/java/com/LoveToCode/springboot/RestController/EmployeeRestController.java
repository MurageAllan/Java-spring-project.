package com.LoveToCode.springboot.RestController;

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

import com.LoveToCode.springboot.Exceptions.EmployeeNotFoundException;
import com.LoveToCode.springboot.entity.Employee;
import com.LoveToCode.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {

		return employeeService.getEmployees();
	}

	@GetMapping("/employees/{id}") // Jackson converts the POJO to JSON
	public Employee getEmployee(@PathVariable int id) { // @PathVariable binds the id to the method parameter

		Employee employee = employeeService.getEmployee(id);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with id: " + id + " is not found");
		} else {
			return employee;
		}
	}

	@PostMapping("/employees") // Jackson converts the request body from JSON TO POJO
	public Employee saveEmployee(@RequestBody Employee employee) { // @RequestBody binds the POJO to the method
																	// parameter

		employee.setEmployeeId(null);
		employeeService.saveOrUpdateEmployee(employee);

		return employee;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {

		employeeService.saveOrUpdateEmployee(employee);

		return employee;
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {

		// get the employee by id
		Employee employee = employeeService.getEmployee(id);

		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with id: " + id + " is not found");
		} else {
			employeeService.deleteEmployee(id);

			return "Deleted customer with id: " + id;
		}
	}
}
