package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Course;
import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// create session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
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
			
			System.out.println("save");
			session.save(theInstructor);
			
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}

}
