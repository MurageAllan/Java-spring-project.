package com.LoveToCode.springboot.service;

import java.util.List;

import com.LoveToCode.springboot.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAllByOrderByLastNameAsc();
	
	public Employee findById(int theId);
	
	public void save(Employee employee);
	
	public void deleteById(int theId);
}
