package com.LoveToCode.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Student;

public class UpdateStudent {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create session.
		Session session = factory.getCurrentSession();
		

		try {
			// Create objects.
			System.out.println("Create an object.");

			Student theStudent = new Student("Allan", "Murage", "nyamuallan@gmail.com");
			Student theStudent1 = new Student("Ian", "Kamau", "kamauian@gmail.com");
			Student theStudent2 = new Student("Derrick", "Macharia", "machariader@gmail.com");

			// Start transaction.
			session.beginTransaction();

			// Save objects.
			session.save(theStudent);
			session.save(theStudent1);
			session.save(theStudent2);

			// commit the transaction.
			session.getTransaction().commit();

			// create session
			Session session1 = factory.getCurrentSession();

			// start a transaction.
			session1.beginTransaction();

			// Retrieve student based on the id

			Student myStudent = session1.get(Student.class, theStudent.getId());
			Student myStudent1 = session1.get(Student.class, theStudent1.getId());
			Student myStudent2 = session1.get(Student.class, theStudent2.getId());

			/*System.out.println("Get complete " + myStudent);
			System.out.println("Get complete " + myStudent1);
			System.out.println("Get complete " + myStudent2);
			*/
			System.out.println("\n");
			// update student.
			myStudent.setEmail("allannyamu@gmail.com");
			myStudent.setFirstName("Lamiq");
			// commit a transaction.
			session1.getTransaction().commit();
			
			System.out.println("\n");
			// new session.
			Session session2 = factory.getCurrentSession();
			
			// new transaction.
			session2.beginTransaction();
			
			// update all email address.
			System.out.println("update all students.");
			session2.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
			
			// commit transaction.
			session2.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}

	}

}
