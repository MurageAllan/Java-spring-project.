package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class OneToOneCRUDbi {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();

		// create Instructor obj
		Instructor theInstructor = new Instructor("Derrick", "Macharia", "machariaderrick@gmail.com");
		// create Instructor detail.
		InstructorDetail theInstructorDetail = new InstructorDetail("https://youtube.com", "partying");
		// associate the two entities.
		theInstructor.setInstructorDetail(theInstructorDetail);
		theInstructorDetail.setInstructor(theInstructor);

		try {
			System.out.println("\n");
			session.beginTransaction();
			// save the instructor detail and in turn save the instructor courtesy of
			// cascade ALL.
			// session.save(theInstructorDetail);

			// load the instructor detail and in turn the instructor courtesy of cascade
			// ALL.
			int Id = 23;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, Id);
			System.out.println(instructorDetail);
			System.out.println("\n");
			// load the instructor alone.
			Instructor instructor = instructorDetail.getInstructor();
			System.out.println(instructor);

			// modify the instructor and instructor detail.
			instructorDetail.setHobby("Dancing");
			instructor.setFirstName("Dero");

			System.out.println("\n");
			// update the instructor detail and in turn the instructor courtesy of cascade
			// All
			session.update(instructorDetail);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();

			factory.close();
		}

	}

}
