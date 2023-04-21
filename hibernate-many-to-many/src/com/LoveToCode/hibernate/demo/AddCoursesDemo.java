package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;
import com.LoveToCode.hibernate.entity.Review;
import com.LoveToCode.hibernate.entity.Student;

public class AddCoursesDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(Review.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		try {
			int theId = 2;
			System.out.println("\n");
			session.beginTransaction();
			
			// get a student from a database.
			Student theStudent = session.get(Student.class,theId );
			// print the student 
			System.out.println("The student is: " + theStudent);
			System.out.println("\n");
			
			// create more courses.
			Course theCourse = new Course("English");
			Course theCourse1 = new Course("Kiswahili");
			
			// add student to the course.
			theCourse.addStudents(theStudent);
			theCourse1.addStudents(theStudent);
			
			// save the courses.
			session.save(theCourse);
			session.save(theCourse1);
			

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
