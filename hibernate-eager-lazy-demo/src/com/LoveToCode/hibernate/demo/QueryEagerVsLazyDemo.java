package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class QueryEagerVsLazyDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		try {

			Integer theId = 1;
			// begin transaction
			session.beginTransaction();
			
			System.out.println("\n");
			// get the instructor from the db using hibernate HQL.
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i JOIN FETCH i.courses where i.id =:theInstructorId", Instructor.class);

			// set the parameter on the query.
			query.setParameter("theInstructorId", theId);

			// execute the query and get the instructor.
			Instructor theInstructor = query.getSingleResult();

			System.out.println("The instructor and the courses are " + theInstructor);
			// commit transaction
			
			
			session.getTransaction().commit();

			session.close();
			
			
			System.out.println("\n");
			System.out.println("Session is closed");

			// This however overrides the lazy loading since this will be eagerly loaded.
			List<Course> theCourse = theInstructor.getCourse();
			for (Course course : theCourse) {
				System.out.println("The instructor associated courses " + course);
			}

			System.out.println("done");
		} finally {
			factory.close();
		}
	}

}
