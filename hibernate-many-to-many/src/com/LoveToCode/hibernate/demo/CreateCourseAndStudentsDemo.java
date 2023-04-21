package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;
import com.LoveToCode.hibernate.entity.Review;
import com.LoveToCode.hibernate.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(Review.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		try {

			System.out.println("\n");
			session.beginTransaction();
			
			// create a course
			Course theCourse = new Course("Physics");
			// saving the course
			session.save(theCourse);
			System.out.println(theCourse);
			System.out.println("\n");
			
			// create students
			Student student = new Student("Allan", "Nyamu" , "nyamuallan@gmail.com");
			Student student1 = new Student("Ivy", "Nyambura" , "nyamburaivy.com");
			Student student2 = new Student("James", "Bond" , "bondjames@gmail.com");
			
			// add the students to the course.
			theCourse.addStudents(student);
			theCourse.addStudents(student1);
			theCourse.addStudents(student2);
			
			System.out.println("saving...");
			// save the students.
			session.save(student);
			session.save(student1);
			session.save(student2);
			
			System.out.println("saving finished." + theCourse.getStudents());
			

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
