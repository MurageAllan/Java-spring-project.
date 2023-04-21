package com.LoveToCode.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LoveToCode.springboot.Dao.EmployeeDao;
import com.LoveToCode.springboot.entity.Employee;

@Service
public class EmployeeServiceHibernateImpl implements EmployeeService {
	
	@Autowired
	@Qualifier("employeeDaoJpaImpl")
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public List<Employee> getEmployees() {

		return employeeDao.getEmployees();
	}

	@Override
	@Transactional
	public Employee getEmployee(int theId) {

		return employeeDao.getEmployee(theId);
	}

	@Override
	@Transactional
	public void saveOrUpdateEmployee(Employee employee) {

		employeeDao.saveOrUpdateEmployee(employee);

	}

	@Override
	@Transactional
	public void deleteEmployee(int theId) {

		employeeDao.deleteEmployee(theId);

	}

}
