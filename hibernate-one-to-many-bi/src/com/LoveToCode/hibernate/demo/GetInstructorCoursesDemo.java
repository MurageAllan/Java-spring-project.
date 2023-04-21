package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

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
			
			// get the instructor from the db
			System.out.println("Retrieving the instructor");
			Instructor theInstructor = session.get(Instructor.class, id);
			
			System.out.println("Retrieving the instructor courses.");
			List<Course>  theCourse = theInstructor.getCourse();
			for(Course course : theCourse ) {
				System.out.println(course);
			}
			
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}

}
