package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class OneToManyCRUDuni {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		// create the instructor.
		Instructor theInstructor = new Instructor("James", "Bond", "bondjames@gmail.com");
		// create the instructor details.
		InstructorDetail instructorDetail = new InstructorDetail("https://youtube", "playing hockey");

		// associate the instructor and the course.
		theInstructor.setInstructorDetail(instructorDetail);
		// create the courses.
		Course theCourse = new Course("calculus");
		Course theCourse1 = new Course("Probability and statistics");
		Course theCourse2 = new Course("Operating system 2");

		try {

			// begin transaction

			session.beginTransaction();
			/*
			 * // add the courses to the instructor theInstructor.addCourses(theCourse);
			 * theInstructor.addCourses(theCourse1); theInstructor.addCourses(theCourse2);
			 * 
			 * // save the courses. session.save(theCourse2); session.save(theCourse1);
			 * session.save(theCourse);
			 */
			// save the instructor
			// session.save(theInstructor);
			System.out.println("\n");
			// load the instructor and the courses.
			int Id = 25;
			Instructor instructor = session.get(Instructor.class, Id);
			System.out.println("The instructor and instructor details are " + instructor);
			
			System.out.println("\n");
			// load the courses
			List<Course> courses = instructor.getCourses();
			System.out.println("The courses are " + courses);
			
			System.out.println("\n");
			
			System.out.println("Deleting the courses");
			// delete the courses.
			session.delete(instructor);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();

			factory.close();
		}

	}

}
