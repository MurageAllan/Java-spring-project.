package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Student;

public class CreateStudent {

	public static void main(String[] args) {
		// Create a sessionfactory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create a session
		Session session = factory.getCurrentSession();

		try {
			// Create a student object..
			System.out.println("Create a new student session");
			Student theStudent = new Student("Murage", "Nyamu", "nyamuallan@gmail.com" );

			// start transaction.
			session.beginTransaction();

			// save the student object.
			System.out.println("saving the student object.");
			session.save(theStudent);

			// commit transaction.
			session.getTransaction().commit();
			System.out.println("Done.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}

}
