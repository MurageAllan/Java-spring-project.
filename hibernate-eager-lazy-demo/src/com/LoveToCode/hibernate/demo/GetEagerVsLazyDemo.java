package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class GetEagerVsLazyDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();
		
		try {
			
			Integer id = 1;
			// begin transaction
			session.beginTransaction();
			
			// get the instructor 
			System.out.println("Retrieving the instructor");
			Instructor theInstructor = session.get(Instructor.class, id);
			System.out.println("The instructor and the instructor detail " + theInstructor);
			
			System.out.println("\n");
			
			
			// Getting the lazy data using a getter that is the courses where the fetch type is lazy.
			System.out.println("Retrieving the instructor courses.");
			List<Course>  theCourse = theInstructor.getCourse();
			for(Course course : theCourse ) {
				System.out.println("The instructor associated courses " + course);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("\n");
			System.out.println("Session is closed");
			
			// This however overrides the lazy loading since this will be eagerly loaded.
			List<Course>  theCourse1 = theInstructor.getCourse();
			for(Course course : theCourse1 ) {
				System.out.println("The instructor associated courses " + course);
			}
			
			System.out.println("done");
		}
		finally {
			factory.close();
		}
	}

}
