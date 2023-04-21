package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Student;

public class PrimaryKey {

	public static void main(String[] args) {
		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create session
		Session session = factory.getCurrentSession();

		try {
			// Create three student objects.
			System.out.println("Create the object");
			
			Student theStudent = new Student("Allan", "Murage", "nyamuallan@gmail.com");
			Student theStudent1 = new Student("Derrick", "Macharia", "machariaDer@gmail.com");
			Student theStudent2 = new Student("Ian", "Kamau", "kamauIan@gmail.com");
			
			// start transaction.
			session.beginTransaction();
			
			// save the student object.
		    System.out.println("saving the students");
			session.save(theStudent);
			session.save(theStudent1);
			session.save(theStudent2);
			
			//commit the transaction.
			System.out.println("Done.");
			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			factory.close();
		}

	}

}
