package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			System.out.println("\n");
			// create some courses.
			Course course1 = new Course("Maths and Computer science");
			Course course2 = new Course("Pharmacy");
			
			// add courses to the instructor
			theInstructor.add(course1);
			theInstructor.add(course2);
			
			System.out.println("\n");
			// save the courses.
			session.save(course1);
			session.save(course2);
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}

}
