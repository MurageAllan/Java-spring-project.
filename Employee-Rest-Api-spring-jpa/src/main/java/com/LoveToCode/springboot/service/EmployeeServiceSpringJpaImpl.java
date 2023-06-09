package com.LoveToCode.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LoveToCode.springboot.Dao.EmployeeRepository;
import com.LoveToCode.springboot.Exceptions.EmployeeNotFoundException;
import com.LoveToCode.springboot.entity.Employee;

@Service
public class EmployeeServiceSpringJpaImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceSpringJpaImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {

		Optional<Employee> result = employeeRepository.findById(theId);

		Employee employee = null;

		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new EmployeeNotFoundException("Did not find employee id - " + theId);
		}
		return employee;
	}

	@Override
	public void save(Employee employee) {

		employeeRepository.save(employee);

	}

	@Override
	public void deleteById(int theId) {

		employeeRepository.deleteById(theId);

	}

}
