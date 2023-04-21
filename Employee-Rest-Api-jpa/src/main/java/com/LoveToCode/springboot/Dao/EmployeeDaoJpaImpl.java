package com.LoveToCode.springboot.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.LoveToCode.springboot.entity.Employee;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> getEmployees() {
		// Create a query to get all employees.
		Query query = entityManager.createQuery("from Employee order by lastName");

		// Get a list of employees from the query.
		List<Employee> employees = query.getResultList();

		return employees;
	}

	@Override
	public Employee getEmployee(int theId) {
		// Get the employee by id.
		Employee employee = entityManager.find(Employee.class, theId);

		return employee;
	}

	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		// Save or update the employee.
		
		Employee theEmployee = entityManager.merge(employee);
		
		employee.setEmployeeId(theEmployee.getEmployeeId());

	}

	@Override
	public void deleteEmployee(int theId) {
		// Create query to delete and employee
		
		Query query = entityManager.createQuery("delete from Employee where employeeId =: id");
		
		query.setParameter("id", theId);
		
		query.executeUpdate();

	}

}
