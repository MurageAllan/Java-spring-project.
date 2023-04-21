package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class OneToOneCRUDuni {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		// create Instructor obj
		Instructor theInstructor = new Instructor("Susan", "Koki", "susanallankoki@gmail.com");
		// create Instructor detail.
		InstructorDetail theInstructorDetail = new InstructorDetail("https://youtube.com", "Swimming");
		// associate the two entities.
		theInstructor.setInstructorDetail(theInstructorDetail);
		try {
			System.out.println("\n");
			session.beginTransaction();

			// save the Instructor and in turn the instructor detail courtesy of cascade.ALL
			// session.save(theInstructor);

			// load the instructor and the instructor detail courtesy of cascade ALL.
			int Id = 22;
			Instructor instructor = session.get(Instructor.class, Id);
			System.out.println("The instructor is " + instructor);

			System.out.println("\n");

			// load the instructor detail only.
			InstructorDetail instructorDetail = instructor.getInstructorDetail();
			System.out.println("The instructor detail is " + instructorDetail);

			// modify the instructor and the instructor detail after their retrieval.
			instructor.setFirstName("Nyamu");
			instructorDetail.setHobby("Dancing");

			// update the instructor applying cascade ALL to update instructor detail also.
			System.out.println("Updating the instructor and instructor detail");
			session.update(instructor);

			// deleting the instructor and applying cascade ALL to delete the instructor
			// detail also.
			System.out.println("\n");
			System.out.println("Deleting the instructor and instructor detail.");
			session.delete(instructor);

			session.getTransaction().commit();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();

			factory.close();
		}

	}
}
