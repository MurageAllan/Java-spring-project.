package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// start a session
		Session session = factory.getCurrentSession();

		try {
			Integer theId = 3;
			// begin transaction
			session.beginTransaction();
			// retrieve the instructor detail.
			InstructorDetail theInstructorDetail = session.get(InstructorDetail.class, theId);

			System.out.println("\n");

			System.out.println("The instructordetail is " + theInstructorDetail);

			System.out.println("\n");

			System.out.println("The instructor is " + theInstructorDetail.getInstructor());
			
			System.out.println("\n");
			
			System.out.println("deleting instractor detail.");
			
			// breaking the bi-direction first before deleting the instructor detail only.
			theInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(theInstructorDetail);
			// commit transaction
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			session.close();
			
			factory.close();
		}

	}

}
