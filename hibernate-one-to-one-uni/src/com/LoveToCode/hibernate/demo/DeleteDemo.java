package com.LoveToCode.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Instructor;
import com.LoveToCode.hibernate.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// start a session
		Session session = factory.getCurrentSession();

		try {
			// create objects
			Instructor theInstructor = new Instructor("Ivy", "Wangechi", "wangechivy@gmail.com");

			InstructorDetail theInstructorDetail = new InstructorDetail("https://www.youtube.com/", "adventure");

			// associate the objects
			theInstructor.setInstructorDetail(theInstructorDetail);

			// begin transaction
			session.beginTransaction();
			
			// saving the obj
			System.out.println("Saving the obj ");
			session.save(theInstructor);
			
			
			System.out.println("\n");
			
			/*// delete the object
			System.out.println("deleting the obj");
			Instructor instructor = session.get(Instructor.class, theInstructor.getId());
			if (instructor != null) {
				session.delete(instructor);

			}*/
			// loading many instructors.
			System.out.println("loading");
			List<Instructor> instructor1 = session.createQuery(" from Instructor ").getResultList();
			for(Instructor intsruct : instructor1) {
				System.out.println( "The instuctors are  " + intsruct);
			}

			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}

	}

}
