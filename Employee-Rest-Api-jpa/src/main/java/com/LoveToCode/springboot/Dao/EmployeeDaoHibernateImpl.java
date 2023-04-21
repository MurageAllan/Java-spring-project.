package com.LoveToCode.springboot.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.LoveToCode.springboot.entity.Employee;

@Repository
public class EmployeeDaoHibernateImpl implements EmployeeDao {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> getEmployees() {
		// get current hibernate session
		Session session = entityManager.unwrap(Session.class);

		// Create a query
		Query<Employee> query = session.createQuery("from Employee order by lastName", Employee.class);

		// Get the result list.
		List<Employee> employees = query.getResultList();

		return employees;
	}

	@Override
	public Employee getEmployee(int theId) {
		// get current session
		Session session = entityManager.unwrap(Session.class);

		Employee employee = session.get(Employee.class, theId);

		return employee;
	}

	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		// get current session
		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(employee);

	}

	@Override
	public void deleteEmployee(int theId) {
		// get current session
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createQuery("delete from Employee where employeeId =: id");

		query.setParameter("id", theId);

		query.executeUpdate();
//		// get an employee by id 
//		Employee employee = session.get(Employee.class, theId);
//		
//		// delete the employee
//		session.delete(employee);

	}

}
