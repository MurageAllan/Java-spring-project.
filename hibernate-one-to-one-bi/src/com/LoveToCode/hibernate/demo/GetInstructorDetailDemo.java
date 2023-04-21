package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// start a session
		Session session = factory.getCurrentSession();

		try {
			Integer theId = 10;
			// begin transaction
			session.beginTransaction();
			// retrieve the instructor detail.
			InstructorDetail theInstructorDetail = session.get(InstructorDetail.class, theId);

			System.out.println("\n");

			System.out.println("The instructordetail is " + theInstructorDetail);

			System.out.println("\n");

			System.out.println("The instructor is " + theInstructorDetail.getInstructor());
			// commit transaction
			session.getTransaction().commit();
		}
		
		finally {
			session.close();
			
			factory.close();
		}

	}

}
