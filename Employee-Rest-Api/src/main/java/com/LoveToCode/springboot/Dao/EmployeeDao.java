package com.LoveToCode.springboot.Dao;

import java.util.List;

import com.LoveToCode.springboot.entity.Employee;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	
	public Employee getEmployee(int theId);
	
	public void saveOrUpdateEmployee(Employee employee);
	
	public void deleteEmployee(int theId);
	
	
}
