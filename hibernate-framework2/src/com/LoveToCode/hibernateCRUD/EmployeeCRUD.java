package com.LoveToCode.hibernateCRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.Employee;

public class EmployeeCRUD {

	public static void main(String[] args) {

		// create a session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class)
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
			
			// commit transaction.
			session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			factory.close();
		}

	}

}
