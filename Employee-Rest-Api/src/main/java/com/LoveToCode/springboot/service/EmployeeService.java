package com.LoveToCode.springboot.service;

import java.util.List;

import com.LoveToCode.springboot.entity.Employee;

public interface EmployeeService {
	public List<Employee> getEmployees();

	public Employee getEmployee(int theId);

	public void saveOrUpdateEmployee(Employee employee);

	public void deleteEmployee(int theId);

}
