package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;
import com.LoveToCode.hibernate.entity.Review;

public class CreateCourseAndReviewsDemo {

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
			// create a new course.
			Course theCourse = new Course("Mechatronics engineering ");

			// add some review.
			System.out.println("Add some reviews \n");
			theCourse.addReviews(new Review("It is a very lengthy but good course."));
			theCourse.addReviews(new Review("It is a quite a tough course."));
			theCourse.addReviews(new Review("Many students try avoiding it while choosing courses."));

			// save the course leveraging the cascade all...
			System.out.println("Save the course and the reviews");
			System.out.println(theCourse);
			System.out.println(theCourse.getReviews());
			System.out.println("\n");
			session.save(theCourse);

			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			session.close();
			factory.close();
		}
	}

}
