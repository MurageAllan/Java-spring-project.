package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;
import com.LoveToCode.hibernate.entity.Review;
import com.LoveToCode.hibernate.entity.Student;

public class GetCoursesDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(Review.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		try {
			int theId = 3;
			System.out.println("\n");
			session.beginTransaction();
			
			// get a student from a database.
			Student theStudent = session.get(Student.class,theId );
			// print the student 
			System.out.println("The student is: " + theStudent);
			System.out.println("\n");
			
			System.out.println(theStudent.getCourses());
			

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
