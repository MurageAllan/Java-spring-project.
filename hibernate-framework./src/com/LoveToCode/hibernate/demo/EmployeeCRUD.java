package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Employee;

public class EmployeeCRUD {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {

		// create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		// Start a session.
		Session session = factory.getCurrentSession();

		// Create a list to store employee objects after retrieval.
		List<Employee> theEmployee;

		try {
			// create students objects.
			System.out.println("Create a new employee session");
			Employee employee = new Employee("Murage", "Nyamu", "Microsoft");
			Employee employee1 = new Employee("Ivy", "Nyamboke", "Safaricom");
			Employee employee2 = new Employee("Stacy", "Wangechi", "Barclays bank");
			Employee employee3 = new Employee("James", "Otieno", "DT Dobbie");
			Employee employee4 = new Employee("Aisha", "Amina", "Deloitte");
			Employee employee5 = new Employee("Duncan", "Mwangi", "Symantec");
			Employee employee6 = new Employee("David", "Murithi", "Volvo");
			Employee employee7 = new Employee("Victor", "Lusui", "Airtel");
			Employee employee8 = new Employee("Mercy", "Njoroge", "Google Africa");
			Employee employee9 = new Employee("Wesley", "Wairagu", "Jubilee Insurance");

			// begin a transaction.
			session.beginTransaction();

			// save objects
			session.save(employee);
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);
			session.save(employee5);
			session.save(employee6);
			session.save(employee7);
			session.save(employee8);
			session.save(employee9);
			System.out.println("\n");
			// commit transaction.
			session.getTransaction().commit();

			// start a new session.
			Session session1 = factory.getCurrentSession();

			// start a new transaction.
			session1.beginTransaction();

			// retrieve a single employee
			System.out.println("Retrieve a single object by id");
			Employee employee10 = session1.get(Employee.class, employee.getEmployeeId());

			System.out.println("\n");

			System.out.println("Retrieve all employees.");
			theEmployee = session1.createQuery("from Employee").getResultList();

			System.out.println("\n");
			// commit transaction.
			session1.getTransaction().commit();

			// start a new session
			Session session2 = factory.getCurrentSession();

			// begin a new transaction.
			session2.beginTransaction();

			System.out.println("Retrieve all employees with firstName Ivy or lastname Njoroge");
			theEmployee = session2.createQuery("from Employee e where e.firstName ='Ivy' or e.lastName = 'Njoroge'")
					.getResultList();

			System.out.println("\n");
			System.out.println("Retrieve all firstnames starting with an A");
			theEmployee = session2.createQuery("from Employee e where e.firstName like 'A%' ").getResultList();

			// commit transaction.
			session2.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}

	}

}
