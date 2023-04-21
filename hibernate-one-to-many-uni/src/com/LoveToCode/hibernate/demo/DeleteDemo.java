package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// start a session
		Session session = factory.getCurrentSession();

		try {

			Integer id = 10;
			// begin transaction
			session.beginTransaction();

			Course theCourse = session.get(Course.class, id);

			System.out.println("Deleting.");

			session.delete(theCourse);
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}

	}

}
