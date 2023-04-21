package com.LoveToCode.springboot.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LoveToCode.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	
	// Add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();// spring data jpa will parse the method name look for a
														// specific format and pattern
														// behind the scenes it creates the appropriate query

	// Add a method to search by name.
	public List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);
}
