package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.LoveToCode.hibernate.entity.Student;

public class CreateReadStudent {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// create a session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// get current session.
		Session session = factory.getCurrentSession();

		try {
			// create student object.
			Student student = new Student("Nyamu", "Murage", "nyamuallan@gmail.com");
			Student student1 = new Student("Jane", "Wangechi", "wangechijane.com");
			Student student2 = new Student("Oscar", "Mwangi", "mwangioscar@gmail.com");
			Student student3 = new Student("Daisy", "Njoroge", "njorogedaisy@gmail.com");
			Student student4 = new Student("Ivy", "Atieno", "atienoivy@gmail.com");
			Student student5 = new Student("Hussein", "Sadam", "sadamhussein@gmail.com");
			Student student6 = new Student("James", "Kamau", "kamaujames@gmail.com");

			// start a transaction to save the students
			session.beginTransaction();

			// save the sudents into the db.
			session.save(student);
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.save(student4);
			session.save(student5);
			session.save(student6);

			// commit the transaction to the db.

			session.getTransaction().commit();

			// start a new session
			Session session1 = factory.getCurrentSession();

			// Start a new transaction.
			session1.beginTransaction();

			// read students using primary keys
			Student theStudent = session1.get(Student.class, student.getId());
			Student theStudent1 = session1.get(Student.class, student1.getId());
			Student theStudent2 = session.get(Student.class, student.getId());
			Student theStudent3 = session.get(Student.class, student.getId());
			

			// commit the transaction.
			session1.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}
