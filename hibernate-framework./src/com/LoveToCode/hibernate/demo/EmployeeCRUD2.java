package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Employee;

public class EmployeeCRUD2 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// create a new session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		// start a new session
		Session session = factory.getCurrentSession();

		try {
			// create employee objects.
			Employee employee1 = new Employee("Daisy", "Mueke", "Techmax solution");
			Employee employee2 = new Employee("Janice", "Wangechi", "Computech");
			Employee employee3 = new Employee("Millie", "Kamau", "Kimisitu sacco");
			Employee employee4 = new Employee("Praxedes", "Achieng", "Equity bank");

			// start a new transaction.

			session.beginTransaction();

			// save employees
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);

			// retrieve an employee
			System.out.println("retrieve employee");
			Employee employee = session.get(Employee.class, employee1.getEmployeeId());

			// update the employees details
			employee.setFirstName("James");
			employee.setLastName("Mworia");
			employee.setCompany("KCB bank");

			System.out.println("\n");

			// retrive the employee
			System.out.println("retrieve the employer after updates");
			Employee employee5 = session.get(Employee.class, employee1.getEmployeeId());

			System.out.println("\n");
			// update all employees
			session.createQuery("update Employee e set e.firstName = 'Nyamu' ").executeUpdate();
			// commit transaction.
			session.getTransaction().commit();

			// new session
			Session session1 = factory.getCurrentSession();

			// new transaction.
			session1.beginTransaction();

			System.out.println("\n");
			System.out.println("delete a single employee");
			Employee employee8 = session1.get(Employee.class, employee1.getEmployeeId());
			session1.delete(employee8);

			System.out.println("\n");
			session1.createQuery(" delete from Employee ").executeUpdate();

			// commit transaction.
			session1.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}
