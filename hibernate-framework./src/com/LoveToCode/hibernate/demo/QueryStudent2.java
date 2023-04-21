package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Student;

public class QueryStudent2 {

	@SuppressWarnings({  "unchecked" })
	public static void main(String[] args) {
		// Create a session Factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a new session.
		Session session = factory.getCurrentSession();

		// create a new student.
		Student student1 = new Student("Murage", "Nyamu", "nyamuallan@gmail.com");
		Student student2 = new Student("Koki", "Susan", "kokiallansusan@gmail.com");
		Student student3 = new Student("Kamau", "Ian", "kamauian@gmail.com");
		Student student4 = new Student("Derrick", "Macharia", "machariaderrick@gmail.com");
		List<Student> student;

		try {
			// begin transaction.
			session.beginTransaction();

			// save Student.
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.save(student4);

			// commit transaction.
			session.getTransaction().commit();

			System.out.println("\n");

			// create a new sessions.
			Session session1 = factory.getCurrentSession();

			// begin transaction.
			session1.beginTransaction();

			// create queries.
			student = session1.createQuery("from Student").getResultList();
			displayStudents(student);
			System.out.println("\n");

			// create queries with first name Nyamu and Atieno.
			student = session1.createQuery("from Student s where s.firstName ='Murage' and s.lastName ='Atieno'")
					.getResultList();
			displayStudents(student);

			// create queries with first name Nyamu or Atieno.
			System.out.println("\n");
			student = session1.createQuery("from Student s where s.firstName = 'Murage' or s.lastName = 'Atieno'")
					.getResultList();
			displayStudents(student);

			// commit transaction.
			session1.getTransaction().commit();
		} finally {
			factory.close();
		}

	}

	public static void displayStudents(List<Student> student) {
		for (Student theStudent : student) {
			System.out.println(theStudent);
		}
	}

}
