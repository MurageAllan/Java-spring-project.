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
			Instructor theInstructor = new Instructor("Nyamu" , "Allan" , "nyamuallan@gmail.com");
			Instructor theInstructor1 = new Instructor("Kamau" , "Waiganjo" , "nyamuallan@gmail.com");
			Instructor theInstructor2 = new Instructor("Daisy" , "Achieng" , "nyamuallan@gmail.com");
			
			
			InstructorDetail theInstructorDetail = new InstructorDetail("https://www.youtube.com/" , "Watching soccer");
			InstructorDetail theInstructorDetail1 = new InstructorDetail("https://www.youtube.com/" , "Swimming");
			InstructorDetail theInstructorDetail2 = new InstructorDetail("https://www.youtube.com/" , "Skating");
			
			// Associate the objects
			theInstructor.setInstructorDetail(theInstructorDetail);
			theInstructor1.setInstructorDetail(theInstructorDetail1);
			theInstructor2.setInstructorDetail(theInstructorDetail2);
			// begin transaction
			session.beginTransaction();
			
			// Also save theInstructorDetail because of cascade.Type all
			
			System.out.println("save");
			session.save(theInstructor);
			session.save(theInstructor1);
			session.save(theInstructor2);
			
			System.out.println("\n");
			
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}

}
