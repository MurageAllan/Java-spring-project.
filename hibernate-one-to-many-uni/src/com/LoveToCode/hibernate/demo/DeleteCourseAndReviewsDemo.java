package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;
import com.LoveToCode.hibernate.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		try {

			System.out.println("\n");
			session.beginTransaction();
			// Get the course
			int theId = 10;
			Course theCourse = session.get(Course.class, theId);
			
			// print the course.
			System.out.println(theCourse);
			System.out.println("\n");
			
			
			// print course and reviews.
			System.out.println(theCourse.getReviews());
			System.out.println("\n");
			System.out.println("Deleting the course and reviews courtesy of cascade review.");
			
			if(theCourse != null) {
				session.delete(theCourse);
			}
			
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
