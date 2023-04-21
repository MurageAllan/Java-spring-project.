package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		// start a new session.
		Session session = factory.getCurrentSession();
		
		try {
			// create objects
			Instructor theInstructor = new Instructor("Nyamu" , "Murage" , "nyamuallan@gmail.com");
			
			InstructorDetail theInstructorDetail = new InstructorDetail("https://www.youtube.com/" , "Watching soccer");
			
			// Associate the objects
			theInstructor.setInstructorDetail(theInstructorDetail);
			// begin transaction
			session.beginTransaction();
			
			// Also save theInstructorDetail because of cascade.Type all
			
			/*System.out.println("save instructor and then the instructor detail");
			session.save(theInstructor);*/
			
			System.out.println("\n");
			System.out.println("Save the instructor detail and then the instructor");
			session.save(theInstructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
