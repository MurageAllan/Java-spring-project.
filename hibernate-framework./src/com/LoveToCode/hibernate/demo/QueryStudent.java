package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Student;

public class QueryStudent {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session.
		Session session = factory.getCurrentSession();

		try {
			// create a transaction
			session.beginTransaction();

			// Query the students,
			List<Student> theStudent = session.createQuery("from Student").getResultList();
			// Display the students.
			displayStudents(theStudent);
			System.out.println("\n");

			// Query students with first name Nyamu
			theStudent = session.createQuery("from Student s where s.firstName = 'Nyamu'").getResultList();
			// Display the students.
			System.out.println("display students with Nyamu as the first name");
			displayStudents(theStudent);
			System.out.println("\n");

			// Query students with first name Nyamu or last name atieno.
			System.out.println("display students with Nyamu as the first name or last name atieno");
			theStudent = session.createQuery("from Student s where s.firstName = 'Nyamu' or s.lastName ='Atieno'")
					.getResultList();
			displayStudents(theStudent);
			System.out.println("\n");
			
			// Query with email like %allan@gmail.com
			System.out.println("display students with emails that end with allan@gmail.com");
			theStudent = session.createQuery("from Student s  where s.email like '%allan@gmail.com'").getResultList();
			displayStudents(theStudent);

			// commit the transaction.
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

	public static void displayStudents(List<Student> theStudent) {
		for (Student student : theStudent) {
			System.out.println(student);
		}
	}

}
